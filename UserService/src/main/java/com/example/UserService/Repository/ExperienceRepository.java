package com.example.UserService.Repository;

import com.example.UserService.Model.Experience;
import com.example.UserService.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends MongoRepository<Experience, Long> {
}
