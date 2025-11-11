package com.api.desafio.back.controller;

import com.api.desafio.back.dto.AuthRequest;
import com.api.desafio.back.dto.AuthResponse;
import com.api.desafio.back.dto.RegisterRequest;
import com.api.desafio.back.model.Role;
import com.api.desafio.back.model.Usuario;
import com.api.desafio.back.repository.UsuarioRepository;
import com.api.desafio.back.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login")
public class AuthController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  // Endpoint de Login
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    // Autentica o usuário (verifica email e senha)
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.senha()
            )
    );

    // Se a autenticação for bem-sucedida, gera o token
    var usuario = usuarioRepository.findByEmail(request.email())
            .orElseThrow(); // Lança exceção se não encontrar
    var token = jwtService.generateToken(usuario);
    return ResponseEntity.ok(new AuthResponse(token));
  }

  // Endpoint de Registro
  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    Usuario novoUsuario = new Usuario();
    novoUsuario.setEmail(request.email());
    novoUsuario.setSenha(passwordEncoder.encode(request.senha())); // Criptografa a senha
    novoUsuario.setRole(request.role().equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER);

    usuarioRepository.save(novoUsuario);

    // Gera um token imediatamente após o registro
    var token = jwtService.generateToken(novoUsuario);
    return ResponseEntity.ok(new AuthResponse(token));
  }
}