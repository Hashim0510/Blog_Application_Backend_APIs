package com.example.blogapprestapi.service.impl;

import com.example.blogapprestapi.entity.post;
import com.example.blogapprestapi.exception.resourceNotFoundException;
import com.example.blogapprestapi.payload.postDTO;
import com.example.blogapprestapi.payload.postResponce;
import com.example.blogapprestapi.repository.postRepository;
import com.example.blogapprestapi.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class postServiceImpl implements postService {

    //injecting postRepository object to save the data into database
    private postRepository postrepository;

    //class is used to easily map or convert entity into DTO(one object to another object convertion)
    private ModelMapper modelMapper;

    //autowired annotation used to inject the postRepository dependency into the postServiceImpl class
    @Autowired
    public postServiceImpl(postRepository postrepository, ModelMapper modelMapper) {

        this.postrepository = postrepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public postDTO createPost(postDTO postDto) {

        //converting postDTO to post entity
        post post = convertDtoToEntity(postDto);
        //save the post entity created instance on the database
        post newPost = postrepository.save(post);
        //convert post Entity into post DTO
        postDto = convertEntityToDto(newPost);
        return postDto;

    }

    @Override
    public postResponce getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection) {


        //create the Sort object to send this as a argument o PageRequest class in dynamic way to handkle both asceneding and descending sorting
        //equalsIgnoreCase() in string will check for string equals without confusion between smaller and upper case
        //inbuilt it is converting all letters into lower case and checking the equals
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                        Sort.by(sortBy).descending();

        //to add pagination support to the fetching all details from database, we can use JPA's inbuild pagination funtions
        //we need to pagination details which has put it into pageable and we can used that pageale object as argument to a findAll() JPA query method
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        //to sort it in descending order use the descending method of PageRequest
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        //the coming list of paginated data should be stored in Page object
        Page<post> posts = postrepository.findAll(pageable);

        //getting paginated and storing in list
        List<post> listOfPosts = posts.getContent();

        //getting the list of post object from database and converting it postDTO objects using streamAPI
        List<postDTO> contents = listOfPosts.stream().map(post -> convertEntityToDto(post)).collect(Collectors.toList());

        //adding additional paginated details like totalNoData in a db for that table, is last page ? etc.,

        postResponce postResponse = new postResponce();

        postResponse.setContent(contents);
        postResponse.setLast(posts.isLast());
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());

        return postResponse;

    }

    @Override
    public postDTO getPostById(long id) {

        //if post is not found for respective id, then findById has function to throw the exception, It is must to pass the supplier functional interface as an argument to the orElseThrow function
        post post = postrepository.findById(id).orElseThrow(() -> new resourceNotFoundException("post", "id", id));

        return convertEntityToDto(post);

    }

    @Override
    public postDTO updatePostById(postDTO postDto, long id) {

        //getting post by id from db
        post post = postrepository.findById(id).orElseThrow(() -> new resourceNotFoundException("post", "id", id));

        post.setContent(postDto.getContent());

        post.setTitle(postDto.getTitle());

        post.setDescription(postDto.getDescription());

        post updatedPost = postrepository.save(post);

        return convertEntityToDto(updatedPost);

    }

    @Override
    public void deletePostById(long id) {
        //getting post by id from db
        post post = postrepository.findById(id).orElseThrow(() -> new resourceNotFoundException("post", "id", id));

        postrepository.delete(post);

    }


    //method to convert DTO to entity object
    private post convertDtoToEntity(postDTO postDto){

        //first argument source class which is going to convert, and 2nd arg is destination object
        post post = modelMapper.map(postDto, post.class);

//        post post = new post();
//
//        post.setId(postDto.getId());
//
//        post.setTitle(postDto.getTitle());
//
//        post.setDescription(postDto.getDescription());
//
//        post.setContent(postDto.getContent());

        return post;

    }

    private postDTO convertEntityToDto(post post){

        postDTO postDTO = modelMapper.map(post, postDTO.class);

//        postDTO postDTOResponse = new postDTO();
//
//        postDTOResponse.setId(post.getId());
//
//        postDTOResponse.setTitle(post.getTitle());
//
//        postDTOResponse.setDescription(post.getDescription());
//
//        postDTOResponse.setContent(post.getContent());

        return postDTO;

    }
}
