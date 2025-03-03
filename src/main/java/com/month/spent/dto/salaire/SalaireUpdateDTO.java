package com.month.spent.dto.salaire;

import java.time.Instant;
import java.util.UUID;

public record SalaireUpdateDTO(
        UUID id,
        Double salaire,
        Instant recu_le
) {
}
