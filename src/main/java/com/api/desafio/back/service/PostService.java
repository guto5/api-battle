package com.api.desafio.back.service;

import com.api.desafio.back.model.Post;
import com.api.desafio.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  // 3.a. POST /post - criar um post
  public Post criarPost(Post post) {
    // O campo dataHora será preenchido automaticamente pelo @CreationTimestamp
    return postRepository.save(post);
  }

  // 3.b. GET /post/count - consultar quantidade de posts
  public long contarPosts() {
    return postRepository.count();
  }

  // 3.c. GET /post - consulta todos posts
  public List<Post> listarTodosPosts() {
    return postRepository.findAll();
  }

  // 3.d. GET /post/id/{id} - consulta 1 post
  public Optional<Post> buscarPostPorId(Long id) {
    return postRepository.findById(id);
  }

  // 3.e. GET /post/exp/{exp} - consulta posts por uma expressão
  public List<Post> buscarPorExpressaoNoComentario(String expressao) {
    return postRepository.findByComentarioContainingIgnoreCase(expressao);
  }
}