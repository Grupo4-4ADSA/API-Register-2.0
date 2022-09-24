package com.autog.register.controller;

import com.autog.register.dto.request.EquipamentoRequest;
import com.autog.register.entity.Equipamento;
import com.autog.register.repository.EmpresaRepository;
import com.autog.register.repository.PredioRepository;
import com.autog.register.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/equipments")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;

    @Autowired
    private EmpresaRepository repository;

//    @Autowired
//    private RegisterRepository registerRepository;

    @Autowired
    private PredioRepository buildingRepository;

    @PostMapping
    public ResponseEntity registerEquipment(@RequestBody @Valid Equipamento newEquipment) {
        return service.registerEquipment(newEquipment);
    }

    @GetMapping("/{idSala}")
    public ResponseEntity listAllEquips(@PathVariable Integer idSala){
        return service.getEquipmentBySala(idSala);
    }

    @GetMapping("/predio/{idPredio}")
    public ResponseEntity getEquipment(@PathVariable Integer idPredio) {
        return service.getEquipmentByPredio(idPredio);
    }

    @GetMapping("/equips/{idEquipment}")
    public ResponseEntity getEquipmentByEquipment(@PathVariable Integer idEquipment) {
        return service.getEquipment(idEquipment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity editEquipment(@PathVariable Integer id, @RequestBody @Valid EquipamentoRequest request) {
        return service.editEquipment(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentById(@PathVariable Integer id) {
        return service.deleteEquipmentById(id);
    }
}
