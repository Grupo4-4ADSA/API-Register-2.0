package com.autog.register.controller;

import com.autog.register.dto.request.SalaRequest;
import com.autog.register.entity.Sala;
import com.autog.register.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class SalaController {

    @Autowired
    private SalaService service;
    
    @PostMapping
    public ResponseEntity registerRoom(@RequestBody @Valid Sala newRoom) {
        return service.registerRoom(newRoom);
    }
    
    @GetMapping("/{idBuilding}")
    public ResponseEntity listRoomsToOncln(@PathVariable Integer idBuilding) {
        return service.listWithClnBox(idBuilding);
    }

    @GetMapping("/all/{idBuilding}")
    public ResponseEntity listAllRooms(@PathVariable Integer idBuilding) {
        return service.listAllRooms(idBuilding);
    }

    @PutMapping("/{id}")
    public ResponseEntity editRoom(@PathVariable Integer id, @RequestBody @Valid SalaRequest request) {
        return service.editRoom(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRoom(@PathVariable Integer id) {
        return service.deleteRoom(id);
    }
}
