package com.autog.register.controller;

import com.autog.register.entity.Predio;
import com.autog.register.repository.PredioRepository;
import com.autog.register.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping("/predio")
public class PredioController {

    @Autowired
    private PredioRepository repository;

    @GetMapping("/{idEmpresa}")
    private ResponseEntity listaPredios(@PathVariable Integer idEmpresa){
        List<Predio> predios = repository.findByIdPredio(idEmpresa);
        return predios.isEmpty() ? noContent().build() : ok().body(predios);
    }

}
