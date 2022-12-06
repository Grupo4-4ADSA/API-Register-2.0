package com.autog.register.repository;

import com.autog.register.dto.response.DadoConsumoMesEquipamento;
import com.autog.register.dto.response.EquipamentoComRegistro;
import com.autog.register.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

    @Query(value = "SELECT e.idEquipamento, e.nome, e.tipo, e.instalacao, e.vidaUtil, e.potencia, e.qtdEquipamento, e.porta, e.fkCLNBox" +
            " FROM Equipamento e join CLNBox c on e.fkCLNBox = c.idCLNBox join Sala s on c.fkSala = s.idSala where idSala = ?1", nativeQuery = true)
    List<Equipamento> getEquipamentoBySala(Integer idSala);

    @Query(value = "select e.idEquipamento, e.nome, e.tipo, e.instalacao, e.vidaUtil, e.potencia, e.qtdEquipamento, e.porta, e.fkCLNBox" +
            " from Equipamento e join CLNBox c on e.fkCLNBox = c.idCLNBox join Sala s on c.fkSala = s.idSala where fkPredio = ?1", nativeQuery = true)
    List<Equipamento> getAllEquipments(Integer idPredio);

    @Transactional
    @Modifying
    @Query("DELETE Equipamento e WHERE e.idEquipamento = ?1")
    void deleteEquipamento(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Equipamento e SET e.tipo = ?2, e.instalacao = ?3, e.vidaUtil = ?4, e.potencia = ?5, e.qtdEquipamento = ?6 WHERE e.idEquipamento = ?1")
    void updateEquipamento(Integer id, String tipo, LocalDate instalacao, Integer vidaUtil, Integer potencia, Integer qtdEquipamento);

    @Query("SELECT e FROM Equipamento e WHERE idEquipamento = ?1")
    List<Equipamento> getEquipamento(Integer idEquipment);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="SELECT * FROM Equipamento e INNER JOIN CLNBox cln on cln.idCLNBox = e.fkCLNBox " +
            " WHERE e.fkCLNBox IN (SELECT idCLNBox FROM CLNBox c INNER JOIN Sala s on c.fkSala = " +
            "s.idSala INNER JOIN Predio p on s.fkPredio = p.idPredio WHERE idPredio = ?1);")
    List<Equipamento> filtrandoEquipamentoConectadosComCLNBox(Integer idPredio);

    @Query("SELECT NEW com.autog.register.dto.response.DadoConsumoMesEquipamento(equ.nome, equ.tipo, equ.clnBox) " +
            "FROM Equipamento equ JOIN equ.clnBox cln JOIN cln.sala sal JOIN sal.predio pre WHERE pre.idPredio = ?1 ")
    List<DadoConsumoMesEquipamento> infoConsumoMesEquipamento(int idPredio);
}
