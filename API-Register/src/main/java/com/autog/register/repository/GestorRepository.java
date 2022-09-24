package com.autog.register.repository;

import com.autog.register.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface GestorRepository extends JpaRepository<Gestor, Integer> {

    @Transactional
    @Modifying
    void deleteByIdGestor(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Gestor m SET m.nome = ?2, m.login = ?3, m.senha = ?4 WHERE m.idGestor = ?1")
    void updateManager(Integer id, String name, String login, String password);

    Gestor findByIdGestor(Integer idManager);
}
