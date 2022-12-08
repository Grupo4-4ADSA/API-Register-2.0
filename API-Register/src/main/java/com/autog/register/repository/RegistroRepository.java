package com.autog.register.repository;

import com.autog.register.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {

    //List<Register> findRegisterByEquipmentAndDateBetween(Integer fkEquipamento, String inicio, String fim);

    List<Registro> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);

}
