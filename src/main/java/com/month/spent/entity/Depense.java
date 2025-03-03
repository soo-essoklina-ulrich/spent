package com.month.spent.entity;

import com.month.spent.entity.user.Utilisateur;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Depense {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private String article;
    private Double prix;

    @ManyToOne
    private Utilisateur utilisateur;

    @CreationTimestamp
    private Instant create_at;

    @UpdateTimestamp
    private Instant update_at;
}
