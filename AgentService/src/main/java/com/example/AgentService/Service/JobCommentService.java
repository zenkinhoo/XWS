package com.example.AgentService.Service;


import com.example.AgentService.Model.JobComment;
import com.example.AgentService.Model.JobOffer;
import com.example.AgentService.Repository.JobCommentRepository;
import com.example.AgentService.Repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import com.example.AgentService.Repository.AgentRepository;
import com.example.AgentService.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class JobCommentService {
    @Autowired
    private JobCommentRepository jobCommentRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    //get all job comments
    public ArrayList<JobComment> getAllJobComments() {
        return jobCommentRepository.findAll();
    }

    //get job comments by JobOfferId
    public ArrayList<JobComment> getCommentsFromOffer(String JobOfferId) {
        ArrayList<JobComment> job_offers = jobCommentRepository.findByJobOfferId(JobOfferId);
        if(job_offers==null)
        {
            throw new IllegalStateException("Ovaj oglas za posao nema komentara");
        }
        return job_offers;
    }

    //create job comment
    public JobComment createComment(JobComment jobComment) {
        return jobCommentRepository.save(jobComment);
    }

    //update job comment
    public JobComment updateComment(String commentId, JobComment jobComment) {
        JobComment jc = jobCommentRepository.findById(commentId);
        JobOffer jo = jobOfferRepository.findById(jc.getJobOfferId());
        Company co = companyRepository.findById(jo.getCompanyId());
        Agent agent = agentRepository.findById(co.getOwnerId());

        if(jc == null)
        {
            throw new IllegalStateException("Komentar ne postoji");
        }
//        if (agent.getRole().equals("Owner")){
//            throw new IllegalStateException("Agent koji je vlasnik ne sme da menja sadrzaj komentara");
//        }

        jc.setPros(jobComment.getPros());
        jc.setCons(jobComment.getCons());
        jc.setRating(jobComment.getRating());
        jc.setSalary(jobComment.getSalary());
        jc.setInterview(jobComment.getInterview());
        jc.setJobOfferId(jobComment.getJobOfferId());

        if (jobCommentRepository.save(jc) != null) {
            System.out.println("Uspesno izmenjene informacije o komentaru");
            return jc;
        }
        System.out.println("Doslo je do greske, informacije o komentaru nisu uspesno izmenjene!");
        return null;
    }

    //delete job comment by id
    public Boolean deleteComment(String commentId) {
        JobComment jobComment = jobCommentRepository.findById(commentId);
        jobCommentRepository.delete(jobComment);
        return true;
    }
}
