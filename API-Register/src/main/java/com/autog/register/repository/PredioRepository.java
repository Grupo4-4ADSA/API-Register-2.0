package com.autog.register.repository;

import com.autog.register.dto.response.InfoEmpresaRelatorio;
import com.autog.register.entity.Predio;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PredioRepository extends JpaRepository<Predio, Integer> {

    List<Predio> findByIdPredio(Integer idPredio);

    @Query("SELECT NEW com.autog.register.dto.response.InfoEmpresaRelatorio(ges.nome, " +
            "emp.razaoSocial, emp.cnpj, pre.nomePredio) " +
            "FROM Predio pre JOIN pre.empresa emp JOIN pre.gestores ges WHERE pre.idPredio = ?1")
    List<InfoEmpresaRelatorio> infoPredioEmpresaGestor(Integer idPredio);
}
