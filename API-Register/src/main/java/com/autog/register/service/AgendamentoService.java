package com.autog.register.service;

import com.autog.register.entity.Agendamento;
import com.autog.register.repository.AgendamentoRepository;
import com.autog.register.utils.BashManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public ResponseEntity registrarAgendamento(Agendamento novoAgendamento) {
        repository.save(novoAgendamento);
        criarCronTab(novoAgendamento.getIdAgendamento(), novoAgendamento.getData(), novoAgendamento.getHorario(), novoAgendamento.getLigar());
        return ResponseEntity.status(201).build();
    }

    private void criarCronTab(Integer idAgendamento, LocalDate data, LocalTime tempo, boolean ligar) {
        Integer minutos = tempo.getMinute();
        Integer horas = tempo.getHour();
        Integer dia = data.getDayOfMonth();
        Integer mes = data.getMonthValue();
        Integer semana = data.getDayOfWeek().getValue();

        BashManager.executeBashCommand(String.format("echo '\n%d %d %d %d %d curl http://34.207.182.80:3000/ -v' >> agendamentos-cronTab.bash",minutos,horas,dia,mes,semana));
        BashManager.executeBashCommand("crontab agendamentos-cronTab.bash");
    }

    public ResponseEntity listarAgendamentos(Integer idPredio) {
        List<Agendamento> agendamentos = repository.pegarTodosAgendamentos(idPredio);

        if (agendamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agendamentos);
    }

    public ResponseEntity editarAgendamento(Integer id, Agendamento request) {
        if (repository.existsById(id)) {
            repository.updateEquipamento(id, request.getHorario(),request.getData());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deletarAgendamento(Integer id) {
        if (repository.existsById(id)) {
            repository.deletarAgendamento(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
