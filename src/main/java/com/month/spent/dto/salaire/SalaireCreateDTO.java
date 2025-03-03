package com.month.spent.dto.salaire;

import java.time.Instant;

public record SalaireCreateDTO(
Double salaire,
Instant recu_le
) {
}
