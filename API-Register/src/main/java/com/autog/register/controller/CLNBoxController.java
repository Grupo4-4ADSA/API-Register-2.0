package com.autog.register.controller;

import com.autog.register.entity.CLNBox;
import com.autog.register.service.CLNBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clnboxex")
public class CLNBoxController {
    @Autowired
    private CLNBoxService service;

    @PostMapping
    public ResponseEntity registerCLNBox(@RequestBody @Valid CLNBox newCLNBox) {
        return service.registerCLNBox(newCLNBox);
    }

    @GetMapping
    public ResponseEntity getCLNBoxex() {
        return service.getCLNBox();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCLNBoxById(@PathVariable Integer id) {
        return service.deleteCLNBoxById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCLNBox(
            @PathVariable Integer id,
            @RequestBody @Valid CLNBox newCLNBox) {
        return service.editCLNBox(id, newCLNBox);
    }
    
    @GetMapping("/{id}/{ip}")
    public ResponseEntity updateIpCLNBox(@PathVariable int id,@PathVariable String ip){
        return service.updateIpClnBox(id,ip);
    }

}