package com.month.spent.repository;

import com.month.spent.entity.user.Utilisateur;
import com.month.spent.enumpack.TypeDeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByUsername(String username);
    Optional<Utilisateur> findByRole_Libelle(TypeDeRole libelle);
}
