package com.api.desafio.back.controller;

import com.api.desafio.back.model.Post;
import com.api.desafio.back.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin("*")
@Tag(name = "Posts", description = "Endpoints para gerenciamento de posts") // Tag para agrupar os endpoints
public class PostController {

  @Autowired
  private PostService postService;

  // 3.a. POST /post - criar um post
  @Operation(summary = "Cria um novo post", description = "Registra um novo post no banco de dados")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Post criado com sucesso"),
          @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
  })
  @PostMapping
  public ResponseEntity<Post> criarPost(@RequestBody Post post) {
    Post novoPost = postService.criarPost(post);
    return new ResponseEntity<>(novoPost, HttpStatus.CREATED);
  }

  // 3.b. GET /post/count - consultar quantidade de posts
  @Operation(summary = "Conta o total de posts", description = "Retorna a contagem total de posts existentes")
  @GetMapping("/count")
  public ResponseEntity<Long> contarPosts() {
    return ResponseEntity.ok(postService.contarPosts());
  }

  // 3.c. GET /post - consulta todos posts
  @Operation(summary = "Lista todos os posts", description = "Retorna uma lista com todos os posts do banco")
  @GetMapping
  public ResponseEntity<List<Post>> listarTodosPosts() {
    return ResponseEntity.ok(postService.listarTodosPosts());
  }

  // 3.d. GET /post/id/{id} - consulta 1 post
  @Operation(summary = "Busca um post por ID", description = "Retorna um post específico pelo seu ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Post encontrado"),
          @ApiResponse(responseCode = "404", description = "Post não encontrado")
  })
  @GetMapping("/id/{id}")
  public ResponseEntity<Post> buscarPostPorId(@PathVariable Long id) {
    return postService.buscarPostPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  // 3.e. GET /post/exp/{exp} - consulta posts por uma expressão dos comentários
  @Operation(summary = "Busca posts por expressão", description = "Retorna posts que contenham a expressão no comentário (case-insensitive)")
  @GetMapping("/exp/{exp}")
  public ResponseEntity<List<Post>> buscarPorExpressao(@PathVariable String exp) {
    List<Post> posts = postService.buscarPorExpressaoNoComentario(exp);
    return ResponseEntity.ok(posts);
  }
}