package com.example.blogapprestapi.service;


import com.example.blogapprestapi.payload.commentDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface commentService {

    commentDTO createComment(long id, commentDTO commentDto);

    List<commentDTO> getAllCommentsByPostId(long postId);

    commentDTO getCommentBycommentAndPostId(long postId, long commentID);

    commentDTO updateCommentBycommentAndPostId(long postId, long commentId, commentDTO commentDTO);

    void deleteCommentBycommentAndPostId(long postId, long commentId);
}
