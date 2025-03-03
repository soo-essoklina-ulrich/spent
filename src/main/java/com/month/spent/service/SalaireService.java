package com.month.spent.service;

import com.month.spent.dto.salaire.SalaireCreateDTO;
import com.month.spent.dto.salaire.SalaireResponseDTO;
import com.month.spent.dto.salaire.SalaireUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface SalaireService {
    SalaireResponseDTO createSalaire(SalaireCreateDTO salaire);
    SalaireResponseDTO updateSalaire(UUID id,SalaireUpdateDTO salaire);
    List<SalaireResponseDTO> getMyAllSalaires();
}
