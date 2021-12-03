package com.fundamentosspring.tutoriales.repository;

import com.fundamentosspring.tutoriales.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
