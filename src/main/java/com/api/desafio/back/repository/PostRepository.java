package com.api.desafio.back.repository;

import com.api.desafio.back.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByComentarioContainingIgnoreCase(String expressao);
}