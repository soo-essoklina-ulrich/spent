package com.month.spent.entity;

import com.month.spent.entity.user.Utilisateur;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder
public class Salaire {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private Double salaire;
    private Double epargne;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Depense> depense;

    private Instant create_at;

    @UpdateTimestamp
    private Instant update_at;
}
