package com.example.AgentService.Service;

import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import com.example.AgentService.Model.JobOffer;
import com.example.AgentService.Repository.AgentRepository;
import com.example.AgentService.Repository.CompanyRepository;
import com.example.AgentService.Repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service

public class JobOfferService {
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AgentRepository agentRepository;


    // get all job offers
    public ArrayList<JobOffer> getAllOffers() {
        return jobOfferRepository.findAll();
    }

    //get all job offers of specific company
    public ArrayList<JobOffer> getOffersByCompanyId(String companyId) {
        ArrayList<JobOffer> job_offers = jobOfferRepository.findByCompanyId(companyId);
        for (JobOffer o:
             job_offers) {
            System.out.println(o);
        }
        if(job_offers == null)
        {
            throw new IllegalStateException("Kompaniaj nema oglasa za posao");
        }
        return job_offers;
    }

    //create job offer
    public JobOffer createOffer(JobOffer jobOffer) {
        Agent agent = agentRepository.findById(companyRepository.findById(jobOffer.getCompanyId()).getOwnerId());
        if(!agent.getRole().equals("Owner")){
            throw new IllegalStateException("Agent koji nije vlasnik ne sme da kreira ponude za posao");
        }
        return jobOfferRepository.save(jobOffer);
    }

    //update job offers
    public JobOffer updateOffer(String offerId, JobOffer jobOffer) {
        JobOffer offer = jobOfferRepository.findById(offerId);
        Agent agent = agentRepository.findById(companyRepository.findById(offer.getCompanyId()).getOwnerId());

        if(offer == null) {
            throw new IllegalStateException("Ponuda za posao ne postoji");
        }
        if(!agent.getRole().equals("Owner")){
            throw new IllegalStateException("Agent koji nije vlasnik ne sme da menja sadrzaj ponude za posao");
        }

        offer.setPosition(jobOffer.getPosition());
        offer.setDescription(jobOffer.getDescription());
        offer.setLocation(jobOffer.getLocation());
        offer.setRequirements(jobOffer.getRequirements());
        offer.setDaily_activities(jobOffer.getDaily_activities());

        if (jobOfferRepository.save(offer) != null) {
            System.out.println("Uspesno izmenjene informacije o ponudi za posao");
            return offer;
        }
        System.out.println("Doslo je do greske, informacije o ponudi za posao nisu uspesno izmenjene!");
        return null;
    }

    //delete job offer by id
    public Boolean deleteOffer(String offerId) {
        JobOffer jobOffer = jobOfferRepository.findById(offerId);
        jobOfferRepository.delete(jobOffer);
        return true;
    }

    public ArrayList<JobOffer> searchByPosition(String position){
        ArrayList<JobOffer> jobOffers = jobOfferRepository.getByPositionContaining(position);
        return jobOffers;
    }


}
