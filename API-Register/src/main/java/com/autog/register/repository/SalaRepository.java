package com.autog.register.repository;

import com.autog.register.dto.request.SalaRequest;
import com.autog.register.dto.response.SalaComClnBox;
import com.autog.register.dto.response.SalaResponse;
import com.autog.register.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

    @Query("SELECT new com.autog.register.dto.response.SalaResponse(r.idRoom, r.name, r.floor) FROM Sala r where predio.idPredio = ?1")
    List<SalaResponse> selectedList(Integer idBuilding);

    @Query("SELECT new com.autog.register.dto.response.SalaResponse(s.idRoom, s.name, s.floor) FROM Sala s WHERE idRoom NOT IN (SELECT c.sala.idRoom FROM CLNBox c) AND s.predio.idPredio = ?1")
    List<SalaResponse> selecionarSalasSemClnBox(Integer idBuilding);

    @Query("SELECT new com.autog.register.dto.response.SalaComClnBox(r.idRoom, r.name, r.floor, r.clnBox.idCLNBox) FROM Sala r where predio.idPredio = ?1")
    List<SalaComClnBox> selectedListWithClnBox(Integer idPredio);

    @Query("SELECT r FROM Sala r JOIN Empresa c WHERE c.idEmpresa = ?1")
    List<Sala> getSalaByEmpresa(Integer idSala);

    @Transactional
    @Modifying
    void deleteByIdRoom(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Sala r SET r.name = ?2, r.floor = ?3 WHERE r.idRoom = ?1")
    void updateRoom(Integer id, String name, Integer floor);

}
