package com.example.AgentService.Controller;


import com.example.AgentService.Model.Company;
import com.example.AgentService.Service.CompanyService;
import com.example.AgentService.Service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    // get all interviews

    // get interview by id

    // create interview

    //update interview

    //delete interview
}
