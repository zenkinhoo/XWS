package com.example.CommunicationService.Event;

import com.example.CommunicationService.Model.Education;
import com.example.CommunicationService.Model.Experience;
import com.example.CommunicationService.Model.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateEvent extends SagaEvent {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String biography;
    private ArrayList<String> skills;
    private ArrayList<String> interests;
    private ArrayList<Education> education;
    private ArrayList<Experience> experience;
    private ArrayList<String> following;
    private ArrayList<String> followRequests;
    private ArrayList<String> blocked;
    private ArrayList<Notification> notifications;
    private Boolean isPrivate;
}
