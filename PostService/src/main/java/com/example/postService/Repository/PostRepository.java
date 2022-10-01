package com.example.postService.Repository;

import java.util.ArrayList;

import com.example.postService.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostRepository extends MongoRepository<Post, Long>{

    public ArrayList<Post> findAll();
    public ArrayList<Post> findAllByUserId(String userId);
    public Post findById(String postId);
}
