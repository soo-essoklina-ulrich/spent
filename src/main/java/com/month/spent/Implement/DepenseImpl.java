package com.month.spent.Implement;

import com.month.spent.dto.depense.DepenseCreateDTO;
import com.month.spent.dto.depense.DepenseResponseDTO;
import com.month.spent.dto.depense.DepenseUpdateDTO;
import com.month.spent.entity.Depense;
import com.month.spent.exceptions.EntityNotFound;
import com.month.spent.mapper.ResponseMapper;
import com.month.spent.repository.DepenceDAO;
import com.month.spent.security.user.UtilisateurConnecteServie;
import com.month.spent.service.DepenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepenseImpl implements DepenseService {
    private final DepenceDAO depenseDAO;
    private final UtilisateurConnecteServie utilisateurConnecteServie;
    private final ResponseMapper responseMapper;

    public DepenseImpl(DepenceDAO depenseDAO, UtilisateurConnecteServie utilisateurConnecteServie, ResponseMapper responseMapper) {
        this.depenseDAO = depenseDAO;
        this.utilisateurConnecteServie = utilisateurConnecteServie;
        this.responseMapper = responseMapper;
    }

    @Override
    public DepenseResponseDTO createDepense(DepenseCreateDTO depense) {
        Depense newDepense = Depense.builder()
                .article(depense.article())
                .prix(depense.prix())
                .utilisateur(this.utilisateurConnecteServie.getUtilisateurConnecte())
                .build();
        return this.responseMapper.responseDepense(this.depenseDAO.save(newDepense));
    }

    @Override
    public DepenseResponseDTO updateDepense(UUID id, DepenseUpdateDTO depense) {
        Optional<Depense> depenseOptional = this.depenseDAO.findById(id).stream().findFirst();
        if (depenseOptional.isPresent()) {
            if (depenseOptional.get().getId().equals(id)) {
                Depense depenseToUpdate = depenseOptional.get();
                depenseToUpdate.setArticle(depense.article());
                depenseToUpdate.setPrix(depense.prix());
                return this.responseMapper.responseDepense(this.depenseDAO.save(depenseToUpdate));
            } else {
                throw new EntityNotFound("Depense Not Trouve");
            }
        }
        return null;
    }

    @Override
    public List<DepenseResponseDTO> getMyAllDepenses() {
        return this.depenseDAO.findAllByUtilisateur(this.utilisateurConnecteServie.getUtilisateurConnecte()).stream().map(this.responseMapper::responseDepense).collect(Collectors.toList());
    }
}
