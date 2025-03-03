package com.month.spent.dto.salaire;

import com.month.spent.dto.depense.DepenseResponseDTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SalaireResponseDTO(
        UUID id,
        Double salaire,
        Double epargne,
        Instant recu_le,
        List<DepenseResponseDTO> depenses
) {
}
