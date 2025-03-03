package com.month.spent.repository;

import com.month.spent.entity.Salaire;
import com.month.spent.entity.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SalaireDAO extends JpaRepository<Salaire, UUID> {
    List<Salaire> findAllByUtilisateur(Utilisateur utilisateur);
}
