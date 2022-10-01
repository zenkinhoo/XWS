package com.example.AgentService.Service;


import com.example.AgentService.Repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterviewService {
    @Autowired
    InterviewRepository interviewRepository;
}
