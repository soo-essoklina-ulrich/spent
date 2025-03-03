package com.month.spent.repository;

import com.month.spent.entity.user.Role;
import com.month.spent.enumpack.TypeDeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findById(UUID id);
    Role findByLibelle(TypeDeRole libelle);
}
