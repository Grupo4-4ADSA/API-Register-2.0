package com.autog.register.repository;

import com.autog.register.entity.ValorTarifa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ValorTarifaRepository extends JpaRepository<ValorTarifa, Integer> {

    ValorTarifa findByDateBetween(LocalDateTime data1, LocalDateTime date2);
}
