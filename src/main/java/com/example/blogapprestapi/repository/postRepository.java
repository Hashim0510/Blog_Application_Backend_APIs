package com.example.blogapprestapi.repository;

import com.example.blogapprestapi.entity.post;
import org.springframework.data.jpa.repository.JpaRepository;

//this is the repository class for POST entity to do and interact with database for CRUD operations,
//HIBERNATE will take care of creating sql statements for us, we have to use the in-built Jpa repository functions to get, manipulate, save, delete entity values,
public interface postRepository extends JpaRepository<post, Long> {


}
