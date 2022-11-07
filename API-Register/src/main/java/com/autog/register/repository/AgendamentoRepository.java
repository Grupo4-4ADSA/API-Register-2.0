package com.autog.register.repository;

import com.autog.register.entity.Agendamento;
import com.autog.register.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

   // @Query(value = "select e.idEquipamento, e.nome, e.tipo, e.instalacao, e.vidaUtil, e.potencia, e.qtdEquipamento, e.porta, e.fkCLNBox" +
    //        " from Equipamento e join CLNBox c on e.fkCLNBox = c.idCLNBox join Sala s on c.fkSala = s.idSala where fkPredio = ?1", nativeQuery = true)
    @Query(value = "SELECT a.idAgendamento, a.data, a.horario, a.ligar , a.fkSala FROM Agendamento a JOIN Predio p WHERE p.idPredio = ?1", nativeQuery = true)
    List<Agendamento> pegarTodosAgendamentos(Integer idPredio);
}
