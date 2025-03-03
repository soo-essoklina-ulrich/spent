package com.month.spent.service;

import com.month.spent.dto.user.ResponseUtilisateur;
import com.month.spent.dto.user.SaveUtilisateurDTO;
import com.month.spent.dto.user.UpdateUtilisateurDTO;
import com.month.spent.dto.user.authentication.ChangePasswordDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UtilisateurService extends UserDetailsService {

    void createSuprerAdmin(String email, String username, String password);
    List<ResponseUtilisateur> findAll();
    ResponseUtilisateur findByEmail(String email);
    ResponseUtilisateur findByUsername(String username);
    ResponseUtilisateur save(SaveUtilisateurDTO utilisateur);
    ResponseUtilisateur update(UUID id, UpdateUtilisateurDTO utilisateur);
    void delete(UUID id);
    Boolean activateUser(UUID id);
    ResponseUtilisateur userconnecte();
    UserDetails loadUserByUsername(String username);

    Boolean changePassword(@Valid ChangePasswordDTO changePasswordDTO);
}
