package com.example.postService.Repository;

import java.util.ArrayList;
import java.util.List;

import com.example.postService.Model.Comment;
import com.example.postService.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends MongoRepository<Comment, Long>{


}