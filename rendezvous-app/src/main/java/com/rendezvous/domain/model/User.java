package com.rendezvous.domain.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private boolean enable;
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);

    @ManyToMany(fetch = FetchType.EAGER)//toda vez que eu buscar os usuarios, ja traz as permissoes dele.
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))//coluna que faz o trabalho contrario.
    private List<Role> roles;


/*---------------------------------------------UserDetails------------------------------------------------------------*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte suas Roles em GrantedAuthority que o Spring entende
        return roles.stream()
                .flatMap(role-> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
    // Esses três você pode deixar true por enquanto
    // (são para expiração de conta e bloqueio — pode implementar depois)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


/*---------------------------------------------------------------------------------------------------------------------*/
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public boolean isEnable() {return enable;}
    public void setEnable(boolean enable) {this.enable = enable;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public List<Role> getRoles() {return roles;}
    public void setRoles(List<Role> roles) {this.roles = roles;}

    public void enableUser(){setEnable(true);}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
