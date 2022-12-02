package com.autog.register.repository;

import com.autog.register.entity.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PredioRepository extends JpaRepository<Predio, Integer> {

    List<Predio> findByIdPredio(Integer idPredio);

}
