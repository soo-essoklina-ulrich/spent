package com.month.spent.Implement;

import com.month.spent.dto.user.ResponseUtilisateur;
import com.month.spent.dto.user.SaveUtilisateurDTO;
import com.month.spent.dto.user.UpdateUtilisateurDTO;
import com.month.spent.dto.user.authentication.ChangePasswordDTO;
import com.month.spent.entity.user.Role;
import com.month.spent.entity.user.Utilisateur;
import com.month.spent.enumpack.TypeDeRole;
import com.month.spent.exceptions.EntityNotFound;
import com.month.spent.exceptions.NotSameId;
import com.month.spent.exceptions.user.BadEmail;
import com.month.spent.exceptions.user.EmailExiste;
import com.month.spent.exceptions.user.SuperAdminExeciste;
import com.month.spent.exceptions.user.UsernameExiste;
import com.month.spent.mapper.ResponseMapper;
import com.month.spent.repository.UtilisateurDAO;
import com.month.spent.security.user.UtilisateurConnecteServie;
import com.month.spent.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UtilisateurImpl implements UtilisateurService, UserDetailsService {

    private final UtilisateurDAO utilisateurDAO;
    private ResponseMapper responseMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private final UtilisateurConnecteServie utilisateurConnecteServie;


    @Override
    public void createSuprerAdmin(String email, String username, String password) {
        Optional<Utilisateur> superAdmin = this.utilisateurDAO.findByRole_Libelle(TypeDeRole.SUPER_ADMIN);
        if (superAdmin.isPresent()) {
           throw new SuperAdminExeciste("Super Admin Existe");
        } else {
            Utilisateur utilisateur = Utilisateur.builder()
                    .email(email)
                    .username(username)
                    .mdp(passwordEncoder.encode(password))
                    .role(
                            Role.builder()
                                    .libelle(TypeDeRole.SUPER_ADMIN)
                                    .build()
                    )
                    .actif(true)
                    .build();
            if (this.verifierUtilisateurEmail(utilisateur)) {
                this.utilisateurDAO.save(utilisateur);
            } else {
                throw new BadEmail("Email invalide");
            }
        }
    }

    @Override
    public List<ResponseUtilisateur> findAll() {
        List<Utilisateur> utilisateurList = this.utilisateurDAO.findAll();
        List <ResponseUtilisateur> responseUtilisateurs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurList) {
            if (!utilisateur.getRole().getLibelle().equals(TypeDeRole.SUPER_ADMIN)) {
                responseUtilisateurs.add(this.responseMapper.responseUtilisateur(utilisateur));
            }
        }
        return responseUtilisateurs;
    }

    @Override
    public ResponseUtilisateur findByEmail(String email) {
        Optional<Utilisateur> utilisateur =this.utilisateurDAO.findByEmail(email);
        if (utilisateur.isPresent()) {
            return this.responseMapper.responseUtilisateur(utilisateur.get());
        } else {
            throw new EntityNotFound("Utilisateur non trouvé");
        }

    }

    @Override
    public ResponseUtilisateur findByUsername(String username) {
        return this.responseMapper.responseUtilisateur(this.utilisateurDAO.findByUsername(username).orElseThrow(() -> new EntityNotFound("Utilisateur non trouvé")));
    }

    @Override
    public ResponseUtilisateur save(SaveUtilisateurDTO utilisateur) {

        Utilisateur user = Utilisateur.builder()
                .nom(utilisateur.nom())
                .prenom(utilisateur.prenom())
                .email(utilisateur.email())
                .numero(utilisateur.numero())
                .username(utilisateur.username())
                .mdp(passwordEncoder.encode(utilisateur.password()))
                .role(
                        Role.builder()
                                .libelle(TypeDeRole.USER)
                                .build()
                )
                .build();

        if (this.verifierUtilisateurEmail(user)) {
            Utilisateur save = this.utilisateurDAO.save(user);
            return new ResponseUtilisateur(
                    save.getId(),
                    save.getNom(),
                    save.getPrenom(),
                    save.getNumero(),
                    save.getEmail(),
                    save.getUsername(),
                    save.getRole().getLibelle().name(),
                    save.getCreatedAt(),
                    save.getActif()
            );
        } else {
            throw new BadEmail("Email invalide");
        }
    }

    @Override
    public ResponseUtilisateur update(UUID id, UpdateUtilisateurDTO utilisateur) {

        if (!utilisateur.id().toString().equals(id.toString())) {
            throw new NotSameId("Id invalide");
        } else {

            Optional<Utilisateur> user = this.utilisateurDAO.findById(utilisateur.id()).stream().findFirst();
            if (user.isPresent()) {
                Utilisateur userUpdate = user.get();
                userUpdate.setNom(utilisateur.nom());
                userUpdate.setPrenom(utilisateur.prenom());
                userUpdate.setEmail(utilisateur.email());
                userUpdate.setNumero(utilisateur.numero());

                return this.responseMapper.responseUtilisateur(this.utilisateurDAO.save(userUpdate));
            } else {
                throw new EntityNotFound("Utilisateur non trouvé");
            }
        }
    }

    @Override
    public void delete(UUID id) {
        this.utilisateurDAO.deleteById(id);
    }

    @Override
    public Boolean activateUser(UUID id) {
        Optional<Utilisateur> user = this.utilisateurDAO.findById(id);
        if (user.isPresent()) {
            Utilisateur utilisateur = user.get();
            utilisateur.setActif(!utilisateur.getActif());
            this.utilisateurDAO.save(utilisateur);
            return utilisateur.getActif();
        } else {
            throw new EntityNotFound("Utilisateur non trouvé");
        }
    }

    @Override
    public ResponseUtilisateur userconnecte() {
        return this.responseMapper.responseUtilisateur(this.utilisateurConnecteServie.getUtilisateurConnecte());
    }

    private Boolean verifierUtilisateurEmail(Utilisateur utilisateur) {
        if (!utilisateur.getEmail().contains("@") || !utilisateur.getEmail().contains(".")) {
            throw new BadEmail("Email invalide");
        } else {
            Optional<Utilisateur> userEmail = this.utilisateurDAO.findByEmail(utilisateur.getEmail());
            if (userEmail.isPresent()) {
                throw new EmailExiste("Email existe deja");
            } else {
                return verifierUtilisateurUsername(utilisateur);
            }
        }
    }

    private Boolean verifierUtilisateurUsername(Utilisateur utilisateur) {
        Optional<Utilisateur> user = this.utilisateurDAO.findByUsername(utilisateur.getUsername());
        if (user.isPresent()) {
            throw new UsernameExiste("Username existe deja");
        } else {
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurDAO
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouve avec cet Username ou Email"));
    }

    @Override
    public Boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        Utilisateur utilisateur = this.utilisateurConnecteServie.getUtilisateurConnecte();
        if (utilisateur == null) {
            throw new EntityNotFound("Utilisateur non trouvé");
        }
        if (!passwordEncoder.matches(changePasswordDTO.oldPassword(), utilisateur.getMdp())) {
            throw new EntityNotFound("Mot de passe incorrect");
        }
        utilisateur.setMdp(passwordEncoder.encode(changePasswordDTO.newPassword()));
        this.utilisateurDAO.save(utilisateur);
        return true;
    }
}
