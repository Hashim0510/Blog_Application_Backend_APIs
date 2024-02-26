package com.example.blogapprestapi.controller;


import com.example.blogapprestapi.payload.postDTO;
import com.example.blogapprestapi.payload.postResponce;
import com.example.blogapprestapi.service.postService;
import com.example.blogapprestapi.utils.appContants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class postController {

    private postService postservice;

    @Autowired
    public postController(postService postservice) {
        this.postservice = postservice;
    }

    //CREATE POST BLOG REST API
    //sending responseEntity object to the client, actually it will be converted from object into json then
    @PostMapping
    public ResponseEntity<postDTO> createPost(@Valid @RequestBody postDTO postDto){
        //valid annotation validating for listed attributes in an object are having proper values with constraints or not
        return new  ResponseEntity<>(postservice.createPost(postDto), HttpStatus.CREATED);

    }

    //GET ALL POSTS
    @GetMapping
    public postResponce getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = appContants.DEFAULT_POST_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = appContants.DEFAULT_POST_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = appContants.DEFAULT_POST_PAGE_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = appContants.DEFAULT_POST_PAGE_SORT_DIRECTION, required = false) String sortDirection


    ){
        //request param used to optional get the param from client

        return postservice.getAllPosts(pageNo, pageSize, sortBy, sortDirection);

    }

    //GET POST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<postDTO> getPostById(@PathVariable(name = "id") long id){

        return ResponseEntity.ok(postservice.getPostById(id));

    }

    //UPDATE POST BY ID
    @PutMapping("/{id}")
    public ResponseEntity<postDTO> updatePostById(@Valid @RequestBody postDTO postDto, @PathVariable(name = "id") long id){

        return new ResponseEntity<>(postservice.updatePostById(postDto, id), HttpStatus.OK);

    }

    //DELETE POST BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){

        postservice.deletePostById(id);

        return new ResponseEntity<>("Post Entity deleted Successfully.", HttpStatus.OK);

    }


}
