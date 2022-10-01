package com.example.JobOfferService.Service;

import com.example.JobOfferService.Model.JobOffer;
import com.example.JobOfferService.Repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    public JobOffer create(JobOffer offer) {
        return jobOfferRepository.save(offer);
    }
    public JobOffer findByJobOfferId (String id) {
        return jobOfferRepository.getById(id);
    }

    public void deleteByJobOfferId (String id) {
        jobOfferRepository.deleteById(id);
    }

    public void deleteAll () {
        jobOfferRepository.deleteAll();
    }

    public ArrayList<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }


    public ArrayList<JobOffer> findBySearch(String search){
        ArrayList<JobOffer> jobOffers = jobOfferRepository.getByPositionContaining(search);
        return jobOffers;
    }




}
