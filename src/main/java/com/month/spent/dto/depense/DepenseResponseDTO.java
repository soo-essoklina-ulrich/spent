package com.month.spent.dto.depense;

import java.time.Instant;
import java.util.UUID;

public record DepenseResponseDTO(
        UUID id,
        String article,
        Double prix,
        Instant depense_le
) {
}
