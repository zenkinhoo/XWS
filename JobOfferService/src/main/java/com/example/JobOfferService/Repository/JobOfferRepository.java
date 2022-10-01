package com.example.JobOfferService.Repository;

import com.example.JobOfferService.Model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String> {

    ArrayList<JobOffer> findAll();
    JobOffer getById(String jobOfferId);

    public void deleteById(String jobOfferId);
    public void deleteAll();

    public ArrayList<JobOffer> getByPositionContaining(String positionPart);
}
