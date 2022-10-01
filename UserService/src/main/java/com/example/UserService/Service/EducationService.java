package com.example.UserService.Service;

import com.example.UserService.Model.Education;
import com.example.UserService.Repository.EducationRepository;
import com.example.UserService.Repository.ExperienceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public void save(Education education)
    {
        this.educationRepository.save(education);
    }
}
