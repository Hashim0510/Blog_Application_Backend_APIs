package com.example.blogapprestapi.repository;

import com.example.blogapprestapi.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentRepository extends JpaRepository<comment, Long> {

    List<comment> findByPostId(long postId);
}
