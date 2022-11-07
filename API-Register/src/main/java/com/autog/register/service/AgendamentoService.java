package com.autog.register.service;

import com.autog.register.entity.Agendamento;
import com.autog.register.entity.Equipamento;
import com.autog.register.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public ResponseEntity registrarAgendamento(Agendamento novoAgendamento) {
        repository.save(novoAgendamento);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity listarAgendamentos(Integer idPredio) {
        List<Agendamento> agendamentos = repository.pegarTodosAgendamentos(idPredio);

        if (agendamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agendamentos);
    }
}
