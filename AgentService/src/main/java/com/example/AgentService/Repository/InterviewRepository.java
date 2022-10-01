package com.example.AgentService.Repository;

import com.example.AgentService.Model.Company;
import com.example.AgentService.Model.Interview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends MongoRepository<Interview, Long> {

}
