package com.example.postService.Model;


import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String title;
    private String description;
    private String userId;
    private int likes;
    private int dislikes;
    private String imageLink;
    private ArrayList<Comment> comments;
    private ArrayList<String> links;
    private ArrayList<String> likedUserIds;
    private ArrayList<String> dislikedUserIds;

    public ArrayList<Comment> getComments() {
        if(comments==null)
        {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getLinks() {
        if(links==null)
        {
            links = new ArrayList<String>();
        }
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public ArrayList<String> getLikedUserIds() {
        if(likedUserIds==null)
        {
            likedUserIds = new ArrayList<String>();
        }
        return likedUserIds;
    }

    public void setLikedUserIds(ArrayList<String> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }

    public ArrayList<String> getDislikedUserIds() {
        if(dislikedUserIds==null)
        {
            dislikedUserIds = new ArrayList<String>();
        }
        return dislikedUserIds;
    }

    public void setDislikedUserIds(ArrayList<String> dislikedUserIds) {
        this.dislikedUserIds = dislikedUserIds;
    }




    public Post(String id, String title, String description, String userId, int likes, int dislikes, String imageLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }




}
