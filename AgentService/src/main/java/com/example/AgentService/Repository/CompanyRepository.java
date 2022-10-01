package com.example.AgentService.Repository;

import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CompanyRepository extends MongoRepository<Company, Long> {
    Company findByName(String name);
    ArrayList<Company> findAll();
    ArrayList<Company> findByOwnerId(String ownerId);
    ArrayList<Company> findByApproved(Boolean approved);
    Company findById(String companyId);

}
