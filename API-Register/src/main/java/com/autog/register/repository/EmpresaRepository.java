package com.autog.register.repository;

import com.autog.register.dto.response.DadoConsumoMes;
import com.autog.register.dto.response.InfoEmpresaRelatorio;
import com.autog.register.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Transactional
    @Modifying
    void deleteByIdEmpresa(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Empresa s SET s.razaoSocial = ?2, s.cnpj = ?3, s.telefone = ?4, s.email = ?5 WHERE s.id = ?1")
    void updateEmpresa(Integer id, String corporateName, String cnpj, String telephone, String email);

    @Query("select new com.autog.register.dto.response.InfoEmpresaRelatorio(ges.nome, " +
            "emp.razaoSocial, emp.cnpj, pre.nome, end.logradouro, end.numero, end.cep) " +
            "from Empresa emp join emp.gestors ges join emp.predios pre join pre.address end " +
            "where b.idBuilding = ?1")
    InfoEmpresaRelatorio infoEmpresa(int idPredio);

    @Query("select new com.autog.register.dto.response.DadoConsumoMes(r.name, r.floor) " +
            "from Building b join b.rooms r where b.idBuilding = ?1")
    List<DadoConsumoMes> infoConsumoMes(int idPredio);

}
