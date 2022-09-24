package com.autog.register.controller;

import com.autog.register.dto.request.AutenticacaoRequest;
import com.autog.register.entity.Gestor;
import com.autog.register.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/autenticacao")
    public ResponseEntity autenticao(@RequestBody AutenticacaoRequest loginUser){
        Gestor user = repository.findByLoginAndSenha(loginUser.getLogin(), loginUser.getSenha());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
