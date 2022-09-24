package com.autog.register.repository;

import com.autog.register.entity.Predio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PredioRepository extends JpaRepository<Predio, Integer> {

    @Query("SELECT b FROM Predio b JOIN Empresa c WHERE c.idEmpresa = ?1")
    List<Predio> getPredioByEmpresa(Integer idCompany);

    Predio findByIdPredio(Integer idPredio);


}
