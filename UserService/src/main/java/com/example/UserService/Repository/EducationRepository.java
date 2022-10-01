package com.example.UserService.Repository;

import com.example.UserService.Model.Education;
import com.example.UserService.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends MongoRepository<Education, Long> {
}
