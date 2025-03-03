package com.month.spent.controller;

import com.month.spent.dto.depense.DepenseCreateDTO;
import com.month.spent.dto.depense.DepenseResponseDTO;
import com.month.spent.dto.depense.DepenseUpdateDTO;
import com.month.spent.service.DepenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("depense")
public class DepenseController {
    public final DepenseService depenseService;


    @GetMapping()
    public ResponseEntity<List<DepenseResponseDTO>> getallmydepense() {
        return ResponseEntity.status(HttpStatus.OK).body(this.depenseService.getMyAllDepenses());
    }

    @PostMapping()
    public ResponseEntity<DepenseResponseDTO> createdepense(@RequestBody DepenseCreateDTO depenseCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.depenseService.createDepense(depenseCreateDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<DepenseResponseDTO> updatesalaire(@RequestBody DepenseUpdateDTO depenseUpdateDTO, @PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.depenseService.updateDepense(id, depenseUpdateDTO));
    }
}
