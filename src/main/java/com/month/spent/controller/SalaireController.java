package com.month.spent.controller;

import com.month.spent.dto.salaire.SalaireCreateDTO;
import com.month.spent.dto.salaire.SalaireResponseDTO;
import com.month.spent.dto.salaire.SalaireUpdateDTO;
import com.month.spent.service.SalaireService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("salaire")
@AllArgsConstructor
@RestController
public class SalaireController {
    public final SalaireService salaireService;

    @GetMapping
    public ResponseEntity<List<SalaireResponseDTO>> getallmysalaire(){
        return ResponseEntity.status(HttpStatus.OK).body(this.salaireService.getMyAllSalaires());
    }

    @PostMapping
    public ResponseEntity<SalaireResponseDTO> createsalaire(@RequestBody SalaireCreateDTO createDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.salaireService.createSalaire(createDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<SalaireResponseDTO> updatesalaire(@PathVariable("id") UUID id, @RequestBody SalaireUpdateDTO createDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.salaireService.updateSalaire(id, createDTO));
    }

}
