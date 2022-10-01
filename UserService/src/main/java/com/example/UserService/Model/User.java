package com.example.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String gender;

  //  private LocalDate dateOfBirth;

    private String biography;
    private ArrayList<String> skills;
    private ArrayList<String> interests;
    private ArrayList<Education> education;
    private ArrayList<Experience> experience;
    private Boolean isPrivate;

    private ArrayList<String> following;
    private ArrayList<String> followRequests;
    private ArrayList<String> blocked;

    private ArrayList<Notification> notifications;

    public ArrayList<Notification> getNotifications() {
        if(notifications==null)
            notifications = new ArrayList<Notification>();
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    private String apiToken;

    public Boolean isPrivate() {
        return isPrivate;
    }

    public ArrayList<String> getFollowing() {
        if(following==null)
            following = new ArrayList<String>();
        return following;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public ArrayList<String> getFollowRequests() {
        if(followRequests==null)
            followRequests = new ArrayList<String>();
        return followRequests;
    }

    public void setFollowRequests(ArrayList<String> followRequests) {
        this.followRequests = followRequests;
    }

    public ArrayList<String> getBlocked() {
        if(blocked==null)
            blocked = new ArrayList<String>();
        return blocked;
    }

    public void setBlocked(ArrayList<String> blocked) {
        this.blocked = blocked;
    }










    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Education> getEducation() {
        if(education==null)
            education = new ArrayList<Education>();
        return education;
    }

    public void setEducation(ArrayList<Education> education) {
        this.education = education;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<String> getSkills() {
        if(skills==null)
            skills = new ArrayList<String>();
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getInterests() {
        if(interests==null)
            interests = new ArrayList<String>();
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<Experience> getExperience() {
        if(experience==null)
            experience = new ArrayList<Experience>();
        return experience;
    }

    public void setExperience(ArrayList<Experience> experience) {
        this.experience = experience;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
