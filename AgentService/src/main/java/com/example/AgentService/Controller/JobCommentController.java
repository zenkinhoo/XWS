package com.example.AgentService.Controller;


import com.example.AgentService.Model.JobComment;
import com.example.AgentService.Repository.CompanyRepository;
import com.example.AgentService.Service.JobCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import com.example.AgentService.Service.AgentService;
import com.example.AgentService.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JobCommentController {
    @Autowired
    private JobCommentService jobCommentService;

    //get all job comments
    @GetMapping(value = "/job_comments", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobComments() {
        ArrayList<JobComment> job_comments = jobCommentService.getAllJobComments();
        if (job_comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ArrayList<JobComment>>(job_comments, HttpStatus.OK);
    }

    //get job comments by JobOfferId
    @GetMapping(path="/{JobOfferId}/jobComments")
    public ResponseEntity<ArrayList<JobComment>> getJobCommentsFromOffer(@PathVariable String JobOfferId){
        ArrayList<JobComment> job_offers = jobCommentService.getCommentsFromOffer(JobOfferId);
        if (job_offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(job_offers,HttpStatus.OK);
    }

    //create job comment
    @PostMapping(value = "/createJobComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobComment(@RequestBody JobComment jobComment){
        try {
            return new ResponseEntity<JobComment>(jobCommentService.createComment(jobComment) , HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //update job comment
    @PutMapping(path = "/updateJobComment/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJobComment(@PathVariable String commentId, @RequestBody JobComment jobComment){
        try{
            jobCommentService.updateComment(commentId,jobComment);
            return new ResponseEntity<JobComment>(jobComment ,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    //delete job comment by id
    @DeleteMapping(value="/deleteJobComment")
    public ResponseEntity<?> deleteJobComment(@RequestParam(value = "id") String commentId){
        try{
            jobCommentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }
}