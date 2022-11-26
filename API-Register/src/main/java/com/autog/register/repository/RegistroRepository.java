package com.autog.register.repository;

import com.autog.register.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {

    //List<Register> findRegisterByEquipmentAndDateBetween(Integer fkEquipamento, String inicio, String fim);

    List<Registro> findByDateBetween(LocalDateTime inicio, LocalDateTime fim);

}
