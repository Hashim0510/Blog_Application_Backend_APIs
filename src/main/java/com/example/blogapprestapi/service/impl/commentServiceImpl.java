package com.example.blogapprestapi.service.impl;


import com.example.blogapprestapi.entity.comment;
import com.example.blogapprestapi.entity.post;
import com.example.blogapprestapi.exception.blogApiException;
import com.example.blogapprestapi.exception.resourceNotFoundException;
import com.example.blogapprestapi.payload.commentDTO;
import com.example.blogapprestapi.repository.commentRepository;
import com.example.blogapprestapi.repository.postRepository;
import com.example.blogapprestapi.service.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class commentServiceImpl implements commentService {
    
    private postRepository postRepository;
    
    private commentRepository commentRepository;
    private ModelMapper modelMapper;

    public commentServiceImpl(postRepository postRepository, commentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public commentDTO createComment(long postId, commentDTO commentDto) {
        
        comment comment = convertCommentDtoToComment(commentDto);
        //retrieving post using post id
        post post = postRepository.findById(postId).orElseThrow(() -> new resourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        comment addedComment = commentRepository.save(comment);
        return convertCommentToCommentDto(addedComment);

    }

    @Override
    public List<commentDTO> getAllCommentsByPostId(long postId) {
        //get the list of comments using postId
        List<comment> allComments = commentRepository.findByPostId(postId);
        //converting comment to commentDTO using streamAPI
        return allComments.stream().map(comment -> convertCommentToCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public commentDTO getCommentBycommentAndPostId(long postId, long commentId) {
        //retrieve post using post ID
        post post = postRepository.findById(postId).orElseThrow(() -> new resourceNotFoundException("Post", "Id", postId));

        //retrieve comment using comment ID
        comment comment = commentRepository.findById(commentId).orElseThrow(() -> new resourceNotFoundException("comment", "Id", commentId));

        //check for given comment id is related the given post id or not

        if(!Objects.equals(comment.getPost().getId(), post.getId())){

            throw new blogApiException(HttpStatus.BAD_REQUEST, "comment id is not related post id", "dummy");

        }
        return convertCommentToCommentDto(comment);

    }

    @Override
    public commentDTO updateCommentBycommentAndPostId(long postId, long commentId, commentDTO commentDTO) {

        //retrieve post using post ID
        post post = postRepository.findById(postId).orElseThrow(() -> new resourceNotFoundException("Post", "Id", postId));

        //retrieve comment using comment ID
        comment comment = commentRepository.findById(commentId).orElseThrow(() -> new resourceNotFoundException("comment", "Id", commentId));

        //check for given comment id is related the given post id or not

        if(!Objects.equals(comment.getPost().getId(), post.getId())){

            throw new blogApiException(HttpStatus.BAD_REQUEST, "comment id is not related post id", "dummy");

        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setCommentBody(commentDTO.getCommentBody());

        comment newComment = commentRepository.save(comment);

        return convertCommentToCommentDto(newComment);


    }

    @Override
    public void deleteCommentBycommentAndPostId(long postId, long commentId) {

        //retrieve post using post ID
        post post = postRepository.findById(postId).orElseThrow(() -> new resourceNotFoundException("Post", "Id", postId));

        //retrieve comment using comment ID
        comment comment = commentRepository.findById(commentId).orElseThrow(() -> new resourceNotFoundException("comment", "Id", commentId));

        //check for given comment id is related the given post id or not

        if(!Objects.equals(comment.getPost().getId(), post.getId())){

            throw new blogApiException(HttpStatus.BAD_REQUEST, "comment id is not related post id");

        }

        commentRepository.delete(comment);


    }


    //method to convert comment to commentDTO object
    private commentDTO convertCommentToCommentDto(comment comment){

          commentDTO commentDTO = modelMapper.map(comment, commentDTO.class);
//        commentDTO commentDto = new commentDTO();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setCommentBody(comment.getCommentBody());
          return commentDTO;
        
    }
    //method to convert commentDTO to comment object
    private comment convertCommentDtoToComment(commentDTO commentDto){

          comment comment = modelMapper.map(commentDto, comment.class);
//        comment comment = new comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setCommentBody(commentDto.getCommentBody());
          return comment;

    }
    
}
