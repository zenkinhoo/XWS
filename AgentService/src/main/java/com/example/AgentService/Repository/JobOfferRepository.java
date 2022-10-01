package com.example.AgentService.Repository;

import com.example.AgentService.Model.Company;
import com.example.AgentService.Model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, Long> {
    ArrayList<JobOffer> findAll();
    ArrayList<JobOffer> findByCompanyId(String companyId);
    JobOffer findById(String offerId);

    ArrayList<JobOffer> getByPositionContaining(String positionPart);
}