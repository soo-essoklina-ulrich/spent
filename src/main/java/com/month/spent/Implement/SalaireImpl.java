package com.month.spent.Implement;

import com.month.spent.dto.salaire.SalaireCreateDTO;
import com.month.spent.dto.salaire.SalaireResponseDTO;
import com.month.spent.dto.salaire.SalaireUpdateDTO;
import com.month.spent.entity.Salaire;
import com.month.spent.entity.user.Utilisateur;
import com.month.spent.mapper.ResponseMapper;
import com.month.spent.repository.SalaireDAO;
import com.month.spent.security.user.UtilisateurConnecteServie;
import com.month.spent.service.SalaireService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class SalaireImpl implements SalaireService {

    private final SalaireDAO salaireDAO;
    private final ResponseMapper responseMapper;
    private final UtilisateurConnecteServie utilisateurConnecteServie;

    public SalaireImpl(SalaireDAO salaireDAO, ResponseMapper responseMapper, UtilisateurConnecteServie utilisateurConnecteServie) {
        this.salaireDAO = salaireDAO;
        this.responseMapper = responseMapper;
        this.utilisateurConnecteServie = utilisateurConnecteServie;
    }

    @Override
    public SalaireResponseDTO createSalaire(SalaireCreateDTO salaire) {
        Salaire newSalaire = Salaire.builder()
                .salaire(salaire.salaire())
                .utilisateur(this.utilisateurConnecteServie.getUtilisateurConnecte())
                .create_at(salaire.recu_le() != null ? salaire.recu_le() : Instant.now())
                .build();
        return this.responseMapper.responseSalaire(this.salaireDAO.save(newSalaire));
    }

    @Override
    public SalaireResponseDTO updateSalaire(UUID id, SalaireUpdateDTO salaire) {
        Optional<Salaire> salaireToUpdate = this.salaireDAO.findById(id).stream().findFirst();
        if (salaireToUpdate.isPresent()) {
            if (salaireToUpdate.get().getId() == id) {
                salaireToUpdate.get().setSalaire(salaire.salaire());
                salaireToUpdate.get().setCreate_at(salaire.recu_le() != null ? salaire.recu_le() : salaireToUpdate.get().getCreate_at());
                return this.responseMapper.responseSalaire(this.salaireDAO.save(salaireToUpdate.get()));
            } else {
                throw new IllegalArgumentException("Salaire not found");
            }
        }
        return null;
    }

    @Override
    public List<SalaireResponseDTO> getMyAllSalaires() {
        return this.salaireDAO.findAllByUtilisateur(this.utilisateurConnecteServie.getUtilisateurConnecte()).stream().map(
                this.responseMapper::responseSalaire
        ).toList();
    }
}
