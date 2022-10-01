package com.example.postService.Service;


import java.util.ArrayList;

import com.example.postService.Model.Comment;
import com.example.postService.Model.Post;
import com.example.postService.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post save(Post newPost) {
        return postRepository.save(newPost);
    }

    public Post findByPostId(String postId) {
        Post post = postRepository.findById(postId);
        if (post == null) {
            throw new IllegalStateException("Post does not exist!");
        }
        return postRepository.findById(postId);

    }

    public Boolean deletePost(String postId) {
        Post post = this.findByPostId(postId);
        postRepository.delete(post);
        return true;
    }

    public ArrayList<Post> getAllPosts() {
        return postRepository.findAll();
    }


    public Post create(Post post) {
        return postRepository.save(post);
    }

    public void deleteAllPosts() {
        System.out.println("Deleting all posts...");
    }

    public void deleteUserPosts(String userId){
        ArrayList<Post> toDeletePosts = postRepository.findAllByUserId(userId);
        postRepository.deleteAll(toDeletePosts);
    }

    public ArrayList<Post> getAllPostsByUserId(String userId) {
        return postRepository.findAllByUserId(userId);
    }
  /*  public ResponseEntity<Post> updatePost(String postId,
                                           @RequestBody Post p)  {

        Post post = postRepository.findById(postId);

        post.setDescription(p.getDescription());
        post.setLikes(p.getLikes());
        post.setTitle(p.getTitle());
        post.setDislikes(p.getDislikes());
        post.setUserId(p.getUserId());
        post.setComments(p.getComments());
        post.setImageLink(p.getImageLink());
        post.setLikedUserIds(p.getLikedUserIds());
        post.setDislikedUserIds(p.getDislikedUserIds());
        post.setLinks(p.getLinks());

        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);

    }*/
    // 2 usera id proslediti, proveriti dal korisnik prati tog korisnika, ako da onda vrati sve postove
    public Post commentPost(String postId,Comment c)  {

        Post post = postRepository.findById(postId);
        post.getComments().add(c);
        return postRepository.save(post);
    }
    public boolean userAlreadyLiked(String userId,String postId) {

        Post post = postRepository.findById(postId);
        if(post.getLikedUserIds().contains(userId))
            return true;
        return false;
    }
    public boolean userAlreadyDisLiked(String userId,String postId) {
        Post post = postRepository.findById(postId);
        if(post.getDislikedUserIds().contains(userId))
            return true;
        return false;
    }
    public Post likePost(String userId,String postId) {
        Post post = postRepository.findById(postId);
        if(userAlreadyDisLiked(userId,postId))
        {
            post.getLikedUserIds().add(userId);
            post.setLikes(post.getLikes()+1);
            post.getDislikedUserIds().remove(userId);
            post.setDislikes(post.getDislikes()-1);
            return this.postRepository.save(post);
        }
        if(!userAlreadyLiked(userId,postId)) {
            post.getLikedUserIds().add(userId);
            post.setLikes(post.getLikes()+1);
            return this.postRepository.save(post);
        }
        else {
            post.getLikedUserIds().remove(userId);
            post.setLikes(post.getLikes()-1);
            return this.postRepository.save(post);
        }
    }
    public Post dislikePost(String userId,String postId) {
        Post post = postRepository.findById(postId);
        if(userAlreadyLiked(userId,postId))
        {
            post.getLikedUserIds().remove(userId);
            post.setLikes(post.getLikes()-1);
            post.getDislikedUserIds().add(userId);
            post.setDislikes(post.getDislikes()+1);
            return this.postRepository.save(post);
        }
        if(!userAlreadyDisLiked(userId,postId)) {
            post.getDislikedUserIds().add(userId);
            post.setDislikes(post.getDislikes()+1);
            return this.postRepository.save(post);
        }
        else {
            post.setDislikes(post.getDislikes()-1);
            post.getDislikedUserIds().remove(userId);
            return this.postRepository.save(post);
        }
    }
    public void deleteUserComments(String userId){
        ArrayList<Post> posts = postRepository.findAllByUserId(userId);
        for(Post post: posts){
            ArrayList<Comment> postComments = post.getComments();
            for(Comment comment: postComments){
                if(comment.getUserId().compareTo(userId) == 0){
                    postComments.remove(comment);
                }
            }
            post.setComments(postComments);
            postRepository.save(post);
        }
    }
    public void updateUserPosts(String userId, String description){
        ArrayList<Post> posts = postRepository.findAllByUserId(userId);
        for(Post post: posts){
            post.setDescription(description);
            postRepository.save(post);
        }
    }


}
