package com.autog.register.repository;

import com.autog.register.dto.response.DadoConsumoMes;
import com.autog.register.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Transactional
    @Modifying
    void deleteByIdEmpresa(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Empresa s SET s.razaoSocial = ?2, s.cnpj = ?3, s.telefone = ?4, s.email = ?5 WHERE s.id = ?1")
    void updateEmpresa(Integer id, String corporateName, String cnpj, String telephone, String email);

    @Query("SELECT NEW com.autog.register.dto.response.DadoConsumoMes(s.name, s.floor) " +
            "FROM Empresa e JOIN e.predios p JOIN p.salas s WHERE p.idPredio = ?1")
    List<DadoConsumoMes> infoConsumoMes(int idPredio);
}
