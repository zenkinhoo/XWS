package com.example.UserService.Repository;

import com.example.UserService.Model.Notification;
import com.example.UserService.Model.User;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface NotificationRepository extends MongoRepository<Notification, Long> {
    Notification findById(String id);
    ArrayList<Notification> findAll();


}
