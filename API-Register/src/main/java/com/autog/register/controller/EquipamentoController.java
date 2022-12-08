package com.autog.register.controller;

import com.autog.register.dto.request.EquipamentoRequest;
import com.autog.register.entity.Equipamento;
import com.autog.register.entity.Registro;
import com.autog.register.repository.*;
import com.autog.register.service.EquipamentoService;
import com.autog.register.service.FormattedReportService;
import feign.Body;
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

    @Autowired
    private RegistroRepository registerRepository;

    @Autowired
    private PredioRepository buildingRepository;

    @Autowired
    private CLNBoxRepository clnBoxRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

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

    @GetMapping("registro/{idSala}")
    public ResponseEntity listarSalasComUltimoRegistro(@PathVariable Integer idSala) {
        return service.listarSalasComUltimoRegistro(idSala);
    }

    @PatchMapping("/{id}")
    public ResponseEntity editEquipment(@PathVariable Integer id, @RequestBody @Valid EquipamentoRequest request) {
        return service.editEquipment(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEquipmentById(@PathVariable Integer id) {
        return service.deleteEquipmentById(id);
    }

    // Endpoint do Gráfico (Consumo Equipamento)
    @PostMapping("/grafico/{idPredio}")
    public ResponseEntity GraficoDeSeisMeses(@PathVariable int idPredio) {
        return new EquipamentoService().dadosGrafico(idPredio,repository, registerRepository, equipamentoRepository,
                clnBoxRepository);
    }

    @PostMapping("/acao/{idEquipment}")
    public ResponseEntity register(@PathVariable int idEquipment,@RequestBody Registro newRegister) {
        newRegister.setEquipamento(service.getDataEquipment(idEquipment));
        return service.register(newRegister);
    }

}
