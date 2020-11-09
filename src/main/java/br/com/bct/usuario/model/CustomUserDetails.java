package br.com.bct.usuario.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {
    private User user;
    private String name;
    private String codigo;
    private List<String> profiles;

    public CustomUserDetails(Usuario usuario) {
        this.user = new User(user.getUsername(), user.getPassword(), getRoles(usuario));
        this.name = usuario.getNome();
        this.codigo = usuario.getCodigo();
        this.profiles = usuario.getPerfil();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public String getName() {
        return name;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public String getCodigo() {
        return codigo;
    }

    private Collection<? extends GrantedAuthority> getRoles(Usuario usuario) {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        usuario.getPerfilUsuario().forEach(perfilUsuario ->
                perfilUsuario.getPerfil().getPerfilOperacoes().forEach(perfilOperacao ->
                        roles.add(new SimpleGrantedAuthority(perfilOperacao.getOperacao().getCodigo())
                        )));
        return roles;
    }
}
