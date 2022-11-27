package com.autog.register.repository;

import com.autog.register.dto.response.InfoEmpresaRelatorio;
import com.autog.register.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("SELECT NEW com.autog.register.dto.response.InfoEmpresaRelatorio(ende.logradouro, ende.numero, ende.cep) " +
            "FROM Endereco ende JOIN ende.predio pre WHERE pre.idPredio = ?1")
    InfoEmpresaRelatorio infoEmpresaEndereco(Integer idPredio);
}
