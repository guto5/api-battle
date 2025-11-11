package com.api.desafio.back.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  // Para este exemplo, usaremos um papel (ROLE) simples
  @Enumerated(EnumType.STRING)
  private Role role;

  public Usuario() {}

  // --- Getters e Setters ---
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getSenha() { return senha; }
  public void setSenha(String senha) { this.senha = senha; }
  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }

  // --- Métodos do UserDetails ---

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Retorna o "papel" do usuário (ex: "ROLE_ADMIN", "ROLE_USER")
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    // Usaremos o email como "username"
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() { return true; }
  @Override
  public boolean isAccountNonLocked() { return true; }
  @Override
  public boolean isCredentialsNonExpired() { return true; }
  @Override
  public boolean isEnabled() { return true; }
}