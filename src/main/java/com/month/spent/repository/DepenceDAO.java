package com.month.spent.repository;

import com.month.spent.entity.Depense;
import com.month.spent.entity.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;
@Repository
public interface DepenceDAO extends JpaRepository<Depense , UUID> {
    List<Depense> findAllByUtilisateur(Utilisateur utilisateur);
}
