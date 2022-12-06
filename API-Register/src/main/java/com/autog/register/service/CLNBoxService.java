package com.autog.register.service;

import com.autog.register.dto.response.GenericResponse;
import com.autog.register.dto.response.SucessResponse;
import com.autog.register.entity.CLNBox;
import com.autog.register.repository.CLNBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class CLNBoxService {

    @Autowired
    private CLNBoxRepository repository;

    public ResponseEntity registerCLNBox(CLNBox newCLNBox) {
        repository.save(newCLNBox);
        return ResponseEntity.status(201).body(new SucessResponse("CLNBox cadastrado com sucesso!", newCLNBox));
    }

    public ResponseEntity getCLNBox() {
        List<CLNBox> clnBoxes = repository.findAll();
        System.out.println(clnBoxes);
        return clnBoxes.isEmpty() ? noContent().build() : ok(clnBoxes);
    }

    public ResponseEntity editCLNBox(Integer id, CLNBox newCLNBox) {
        if (repository.existsById(id)) {
            repository.updateCLNBox(id, newCLNBox.getQrCode(), newCLNBox.getIp());
            return ResponseEntity.status(200).body(new GenericResponse(true));
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity deleteCLNBoxById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteByIdCLNBox(id);
            return ResponseEntity.status(200).body(new GenericResponse(true));
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity updateIpClnBox(int id, String ip) {
        if (repository.existsById(id)) {
            repository.updateIpCLNBox(id,ip);
            return ResponseEntity.status(200).body(new GenericResponse(true));
        }
        return ResponseEntity.status(404).build();

    }
}