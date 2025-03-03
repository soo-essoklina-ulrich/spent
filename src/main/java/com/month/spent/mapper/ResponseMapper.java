package com.month.spent.mapper;


import com.month.spent.dto.user.ResponseUtilisateur;
import com.month.spent.entity.user.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public ResponseUtilisateur responseUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return new ResponseUtilisateur(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getNumero(),
                utilisateur.getEmail(),
                utilisateur.getUsername(),
                utilisateur.getRole().getLibelle().name(),
                utilisateur.getCreatedAt(),
                utilisateur.getActif()
        );
    }
}
