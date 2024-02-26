package com.example.blogapprestapi.controller;

import com.example.blogapprestapi.payload.commentDTO;
import com.example.blogapprestapi.service.impl.commentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class commentController {

    private commentServiceImpl commentServiceImpl;

    public commentController(commentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<commentDTO> createComment(@PathVariable(name = "postId") long postId, @Valid @RequestBody commentDTO commentDto){

        return new ResponseEntity<>(commentServiceImpl.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @GetMapping("post/{postId}/comment")
    public List<commentDTO> getAllCommentsByPostId(@PathVariable(name  = "postId") long postId){

        return commentServiceImpl.getAllCommentsByPostId(postId);

    }

    @GetMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<commentDTO> getCommentByPostAndCommentId(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId
    ){

        return new ResponseEntity<>(commentServiceImpl.getCommentBycommentAndPostId(postId, commentId), HttpStatus.OK);

    }

    @PutMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<commentDTO> updateCommentByPostAndCommentId(
            @PathVariable(name="postId") long postId,
            @PathVariable(name="commentId") long commentId,
            @Valid @RequestBody commentDTO commentDTO

    ){

        return ResponseEntity.ok(commentServiceImpl.updateCommentBycommentAndPostId(postId, commentId, commentDTO));

    }

    @DeleteMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<String> updateCommentByPostAndCommentId(
            @PathVariable(name="postId") long postId,
            @PathVariable(name="commentId") long commentId
    ){
        commentServiceImpl.deleteCommentBycommentAndPostId(postId, commentId);
        return ResponseEntity.ok("comment Deleted successfully !!!");
    }
}
