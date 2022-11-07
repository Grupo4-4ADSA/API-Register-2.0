package com.autog.register.controller;

import com.autog.register.entity.Agendamento;
import com.autog.register.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @PostMapping
    public ResponseEntity registrarAgendamento(@RequestBody @Valid Agendamento novoAgendamento) {
        return service.registrarAgendamento(novoAgendamento);
    }

    @GetMapping("/predio/{idPredio}")
    public ResponseEntity listarAgendamentos(@PathVariable Integer idPredio) {
        return service.listarAgendamentos(idPredio);
    }
}
