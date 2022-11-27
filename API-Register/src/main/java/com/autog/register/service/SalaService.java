package com.autog.register.service;

import com.autog.register.dto.request.SalaRequest;
import com.autog.register.dto.response.SalaComClnBox;
import com.autog.register.dto.response.SalaResponse;
import com.autog.register.dto.response.SucessResponse;
import com.autog.register.entity.Sala;
import com.autog.register.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@Service
public class SalaService {

    @Autowired
    private SalaRepository repository;

    public ResponseEntity registerRoom(Sala newRoom) {
        repository.save(newRoom);

        return status(201).body(new SucessResponse("Sala cadastrada com sucesso!", newRoom));
    }

    public ResponseEntity listWithClnBox(Integer idBuilding) {
        List<SalaComClnBox> selectedList = repository.selectedListWithClnBox(idBuilding);
        return selectedList.isEmpty() ? noContent().build() : ok().body(selectedList);
    }

    public ResponseEntity listAllRooms(Integer idBuilding) {
        List<SalaResponse> selectedList = repository.selectedList(idBuilding);
        return selectedList.isEmpty() ? noContent().build() : ok().body(selectedList);
    }

    @CrossOrigin
    public ResponseEntity editRoom(Integer id, SalaRequest request) {
        if (repository.existsById(id)) {
            repository.updateRoom(id, request.getName(), request.getFloor());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    public ResponseEntity deleteRoom(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteByIdRoom(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
