package com.example.postService.Service;

import com.example.postService.Model.Comment;
import com.example.postService.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll (){
        return commentRepository.findAll();
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
