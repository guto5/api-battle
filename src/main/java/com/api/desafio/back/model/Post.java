package com.api.desafio.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts") // Define o nome da tabela no banco
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String quem;

  @CreationTimestamp // Define a data e hora automaticamente na criação
  @Column(nullable = false, updatable = false)
  private LocalDateTime dataHora;

  @Column(columnDefinition = "TEXT") // Permite comentários mais longos
  private String comentario;

  // Este é o [campo_extra] que inventei: curtidas
  @Column(columnDefinition = "integer default 0")
  private Integer curtidas = 0;

  // --- Construtores, Getters e Setters ---

  public Post() {
    // Construtor padrão
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getQuem() {
    return quem;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public String getComentario() {
    return comentario;
  }

  public Integer getCurtidas() {
    return curtidas;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setQuem(String quem) {
    this.quem = quem;
  }

  // Não precisamos de setter para dataHora, pois é gerado automaticamente

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public void setCurtidas(Integer curtidas) {
    this.curtidas = curtidas;
  }
}