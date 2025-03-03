package com.month.spent.enumpack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum TypeDeRole {
    SUPER_ADMIN(
            Set.of(
                    TypePermission.SUPER_ADMINISTRATEUR_CREATE,
                    TypePermission.SUPER_ADMINISTRATEUR_READ,
                    TypePermission.SUPER_ADMINISTRATEUR_UPDATE,
                    TypePermission.SUPER_ADMINISTRATEUR_DELETE,

                    TypePermission.ADMINISTRATEUR_CREATE,
                    TypePermission.ADMINISTRATEUR_READ,
                    TypePermission.ADMINISTRATEUR_UPDATE,
                    TypePermission.ADMINISTRATEUR_DELETE,

                    TypePermission.UTILISATEUR_CREATE,
                    TypePermission.UTILISATEUR_READ,
                    TypePermission.UTILISATEUR_UPDATE
            )
    ),
    ADMIN(
            Set.of(
                    TypePermission.ADMINISTRATEUR_CREATE,
                    TypePermission.ADMINISTRATEUR_READ,
                    TypePermission.ADMINISTRATEUR_UPDATE,
                    TypePermission.ADMINISTRATEUR_DELETE,

                    TypePermission.UTILISATEUR_CREATE,
                    TypePermission.UTILISATEUR_READ,
                    TypePermission.UTILISATEUR_UPDATE
            )
    ),
    USER(
            Set.of(
                    TypePermission.UTILISATEUR_CREATE,
                    TypePermission.UTILISATEUR_READ,
                    TypePermission.UTILISATEUR_UPDATE
            )
    );

    final Set<TypePermission> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions().stream().map(
            permission -> new SimpleGrantedAuthority(permission.name())
        ).collect(Collectors.toList());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
