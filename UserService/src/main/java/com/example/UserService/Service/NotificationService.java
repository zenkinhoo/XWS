package com.example.UserService.Service;


import com.example.UserService.Model.Notification;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.NotificationRepository;
import com.example.UserService.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;



    @Autowired
    private UserRepository userRepository;


    public void save(Notification notification)
    {
        this.notificationRepository.save(notification);
    }
    public Notification markAsRead(String userId,String notificationId)
    {
        User user = userRepository.findById(userId);
        Notification notification = notificationRepository.findById(notificationId);
        if(notification.getId()!=null)
        {
            System.out.println(notification.getId());
        }
        else {
            System.out.println("null je id notifikacije pronadjene");
        }
        ArrayList<Notification> userNotifications = user.getNotifications();
        ArrayList<Notification> updatedUserNotifications = user.getNotifications();
        for (Notification userNotification:userNotifications
             ) {
            if(userNotification.getId().equals(notificationId))
            {

                userNotification.setRead(true);
                int index = userNotifications.indexOf(userNotification);
                System.out.println(Integer.toString(index));

                updatedUserNotifications.set(index,userNotification);
                user.setNotifications(updatedUserNotifications);
                userRepository.save(user);
                notificationRepository.save(notification);
                System.out.println("Notifikacija markirana kao procitana");
                return userNotification;

            }
        }
        System.out.println("Doslo je do greske, notifikacija nije markirana kao procitana");

        return notification;
    }
    public Notification findById(String notificationId)
    {
        return this.notificationRepository.findById(notificationId);
    }

    public ArrayList<Notification> getAllNotifications()
    {
        return this.notificationRepository.findAll();
    }



}
