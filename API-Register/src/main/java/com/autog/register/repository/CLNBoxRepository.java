package com.autog.register.repository;

import com.autog.register.entity.CLNBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CLNBoxRepository extends JpaRepository<CLNBox, Integer> {

    @Transactional
    @Modifying
    void deleteByIdCLNBox(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE CLNBox c SET c.qrCode = ?2, c.ip = ?3 WHERE c.idCLNBox = ?1")
    void updateCLNBox(Integer id, String qrCode, String ip);

    @Transactional
    @Modifying
    @Query("UPDATE CLNBox c SET c.ip = ?2 WHERE c.idCLNBox = ?1")
    void updateIpCLNBox(Integer id,  String ip);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="select * from CLNBox c join Sala s on c.fkSala = s.idSala join " +
            "Predio p on s.fkPredio = p.idPredio where idPredio = ?1")
    List<CLNBox> filtrandoCLNBoxConectadosComEquipamentosDoPredio(Integer idPredio);

}