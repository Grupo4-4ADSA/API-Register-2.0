package com.autog.register.repository;

import com.autog.register.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Gestor, Long> {

    Gestor findByLoginAndSenha(String login, String senha);

}
