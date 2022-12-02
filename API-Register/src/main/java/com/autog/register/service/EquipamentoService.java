package com.autog.register.service;

import com.autog.register.dto.request.EquipamentoRequest;
import com.autog.register.dto.response.EquipamentoComRegistro;
import com.autog.register.dto.response.EquipamentoResponse;
import com.autog.register.entity.Equipamento;
import com.autog.register.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    public ResponseEntity registerEquipment(Equipamento newEquipment) {
        repository.save(newEquipment);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity getEquipmentByPredio(Integer idPredio) {
        List<Equipamento> equipments = repository.getAllEquipments(idPredio);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    public ResponseEntity getEquipmentBySala(Integer idSala) {
        FiltroSingleton.getInstancia().setEquipamentoComRegistro(false);
        List<Equipamento> equipments = repository.getEquipamentoBySala(idSala);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    public ResponseEntity listarSalasComUltimoRegistro(Integer idSala) {
        FiltroSingleton.getInstancia().setEquipamentoComRegistro(true);
        List<Equipamento> equipamentos = repository.getEquipamentoBySala(idSala);

        if (equipamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipamentos);
    }

    public ResponseEntity getEquipment(Integer idEquipamento) {
        List<Equipamento> equipments = repository.getEquipamento(idEquipamento);

        if (equipments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipments);
    }

    private LocalDate convertStringToLocalDate(String dateToBeFormatted) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US );
        LocalDate date = LocalDate.parse(dateToBeFormatted, formatter);
        return date;
    }

    public ResponseEntity editEquipment(Integer id, EquipamentoRequest request) {
        if (repository.existsById(id)) {
            repository.updateEquipamento(id, request.getName());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteEquipmentById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteEquipamento(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
