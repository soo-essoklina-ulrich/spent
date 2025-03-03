package com.month.spent.mapper;


import com.month.spent.dto.depense.DepenseResponseDTO;
import com.month.spent.dto.salaire.SalaireResponseDTO;
import com.month.spent.dto.user.ResponseUtilisateur;
import com.month.spent.entity.Depense;
import com.month.spent.entity.Salaire;
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


    public SalaireResponseDTO responseSalaire(Salaire salaire) {
        if (salaire == null) {
            return null;
        }

        return new SalaireResponseDTO(
                salaire.getId(),
                salaire.getSalaire(),
                salaire.getEpargne() == null ? 0 : salaire.getEpargne(),
                salaire.getCreate_at(),
                salaire.getDepense().stream().map(this::responseDepense).toList()

        );
    }

    public DepenseResponseDTO responseDepense(Depense depense) {
        if (depense == null) {
            return null;
        }

        return new DepenseResponseDTO(
                depense.getId(),
                depense.getArticle(),
                depense.getPrix(),
                depense.getCreate_at()
        );
    }
}
