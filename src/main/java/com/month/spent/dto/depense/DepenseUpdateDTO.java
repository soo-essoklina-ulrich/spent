package com.month.spent.dto.depense;

import java.util.UUID;

public record DepenseUpdateDTO(
        UUID id,
        String article,
        Double prix
) {
}
