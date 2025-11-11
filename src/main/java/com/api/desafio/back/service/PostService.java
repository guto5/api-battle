package com.api.desafio.back.service;

import com.api.desafio.back.model.Post;
import com.api.desafio.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  // 3.a. POST /post - criar um post
  // Sempre que este método for chamado, o cache "postCount" será limpo.
  @CacheEvict(value = "postCount", allEntries = true)
  public Post criarPost(Post post) {
    return postRepository.save(post);
  }

  // 3.b. GET /post/count - consultar quantidade de posts
  // O resultado deste método será armazenado em um cache chamado "postCount"
  @Cacheable("postCount")
  public long contarPosts() {
    // A linha abaixo só será executada se o cache não existir.
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