// Crie um novo pacote 'dto'
package com.api.desafio.back.dto;

// DTO para requisição de registro
public record RegisterRequest(String email, String senha, String role) {}

