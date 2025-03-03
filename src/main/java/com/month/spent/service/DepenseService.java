package com.month.spent.service;

import com.month.spent.dto.depense.DepenseCreateDTO;
import com.month.spent.dto.depense.DepenseResponseDTO;
import com.month.spent.dto.depense.DepenseUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface DepenseService {
    DepenseResponseDTO createDepense(DepenseCreateDTO depense);
    DepenseResponseDTO updateDepense(UUID id,DepenseUpdateDTO depense);
    List<DepenseResponseDTO> getMyAllDepenses();
}
