package com.example.AgentService.Controller;


import com.example.AgentService.Model.JobOffer;
import com.example.AgentService.Service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    //get all job offers
    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobOffers() {
        ArrayList<JobOffer> job_offers = jobOfferService.getAllOffers();
        if (job_offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ArrayList<JobOffer>>(job_offers, HttpStatus.OK);
    }

    //get all job offers of specific company
    @GetMapping(path = "/jobOffers/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<JobOffer>> getJobOffersByCompanyId(@PathVariable String companyId){
        ArrayList<JobOffer> job_offers = jobOfferService.getOffersByCompanyId(companyId);
        if (job_offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(job_offers,HttpStatus.OK);
    }

    //create job offer
    @PostMapping(value = "/createOffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobOffer(@RequestBody JobOffer newJobOffer){
        try {
            return new ResponseEntity<JobOffer>(jobOfferService.createOffer(newJobOffer) , HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //update job offers
    @PutMapping(path = "/updateOffer/{offerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJobOffer(@PathVariable String offerId, @RequestBody JobOffer jobOffer){
        try{
            jobOfferService.updateOffer(offerId,jobOffer);
            return new ResponseEntity<JobOffer>(jobOffer , HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    //delete job offer by id
    @DeleteMapping(value="/deleteOffer")
    public ResponseEntity<?> deleteJobOffer(@RequestParam(value = "id") String offerId){
        try{
            jobOfferService.deleteOffer(offerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE, path = "search/{position}")
    public ResponseEntity<?> getJobOffersBySearch(@PathVariable String position){
        ArrayList<JobOffer> list = jobOfferService.searchByPosition(position);
        if(list.size() > 0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
