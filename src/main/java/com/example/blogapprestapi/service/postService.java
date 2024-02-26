package com.example.blogapprestapi.service;

import com.example.blogapprestapi.payload.postDTO;
import com.example.blogapprestapi.payload.postResponce;

import java.util.List;

public interface postService {

    postDTO createPost(postDTO postDto);

    postResponce getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);

    postDTO getPostById(long id);

    postDTO updatePostById(postDTO postDto, long id);

    void deletePostById(long id);


}
