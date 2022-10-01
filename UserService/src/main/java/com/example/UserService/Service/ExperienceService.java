package com.example.UserService.Service;


import com.example.UserService.Model.Experience;
import com.example.UserService.Repository.ExperienceRepository;
import com.example.UserService.Repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public void save(Experience experience)
    {
        this.experienceRepository.save(experience);
    }
}
