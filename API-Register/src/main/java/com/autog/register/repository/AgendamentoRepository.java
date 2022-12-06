package com.autog.register.repository;

import com.autog.register.entity.Agendamento;
import com.autog.register.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query(value = "SELECT a.idAgendamento, a.data, a.horario, a.ligar , a.fkSala FROM Agendamento a JOIN Predio p WHERE p.idPredio = ?1", nativeQuery = true)
    List<Agendamento> pegarTodosAgendamentos(Integer idPredio);

    @Transactional
    @Modifying
    @Query("DELETE Agendamento a WHERE a.idAgendamento = ?1")
    void deletarAgendamento(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Agendamento a SET a.horario = ?2, a.data = ?3, a.ligar = ?4 WHERE a.idAgendamento = ?1")
    void updateEquipamento(Integer id, LocalTime horario, LocalDate data, boolean ligar);
}
