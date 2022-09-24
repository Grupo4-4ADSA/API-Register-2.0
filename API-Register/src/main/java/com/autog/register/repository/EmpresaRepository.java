package com.autog.register.repository;

import com.autog.register.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Transactional
    @Modifying
    void deleteByIdEmpresa(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Empresa s SET s.razaoSocial = ?2, s.cnpj = ?3, s.telefone = ?4, s.email = ?5 WHERE s.id = ?1")
    void updateEmpresa(Integer id, String corporateName, String cnpj, String telephone, String email);

//    @Query("select new com.autog.register.dto.response.FormattedReport(m.nameManager, " +
//            "c.corporateName, c.cnpj, b.nameBuilding, a.publicPlace, a.number, a.cep) " +
//            "from Company c join c.gestors m join c.buildings b join b.address a " +
//            "where b.idBuilding = ?1")
//    FormattedReport corpoUm(int idPredio);
//
//    @Query("select new com.autog.register.dto.response.MonthlyConsumption(r.name, r.floor) " +
//            "from Building b join b.rooms r where b.idBuilding = ?1")
//    List<MonthlyConsumption> corpoDois(int idPredio);

}
