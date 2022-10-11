package com.autog.register.service;

import com.autog.register.entity.Agendamento;
import com.autog.register.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public ResponseEntity registrarAgendamento(Agendamento novoAgendamento) {
        repository.save(novoAgendamento);
        return ResponseEntity.status(201).build();
    }
}
