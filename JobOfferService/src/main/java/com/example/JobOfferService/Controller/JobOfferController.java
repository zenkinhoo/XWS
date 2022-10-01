package com.example.JobOfferService.Controller;

import com.example.JobOfferService.Model.JobOffer;
import com.example.JobOfferService.Service.JobOfferService;
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

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World from Spring Boot";
    }


    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody JobOffer newOffer){
        try {
            return new ResponseEntity<JobOffer>(jobOfferService.create(newOffer) , HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(
            value = "/jobOffers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobOffers() {
        ArrayList<JobOffer> offers = jobOfferService.getAllJobOffers();
        if (offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ArrayList<JobOffer>>(offers, HttpStatus.OK);
    }

    @GetMapping(path = "/jobOfferId/{jobOfferId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByJobOffertId(@PathVariable String jobOfferId) {
        JobOffer offer = jobOfferService.findByJobOfferId(jobOfferId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<JobOffer>(offer, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteAll")
    public void deleteAllJobOffers() {
        jobOfferService.deleteAll();
    }

    @DeleteMapping(path = "/delete/{jobOfferId}")
    public void deleteJobOffer(@PathVariable String jobOfferId) {
        jobOfferService.deleteByJobOfferId(jobOfferId);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE, path = "search/{search}")
    public ResponseEntity<?> getJobOffersBySearch(@PathVariable String search){
        ArrayList<JobOffer> list = jobOfferService.findBySearch(search);
        if(list.size() > 0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
