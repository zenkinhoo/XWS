package com.example.AgentService.Service;



import com.example.AgentService.Helper.EmailValidator;
import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import com.example.AgentService.Repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    private EmailValidator emailValidator = new EmailValidator();


    public ArrayList<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent create(Agent agent) {

        boolean isValidEmail = emailValidator.test(agent.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email nije u validnom formatu!");
        }
        boolean agentExists = agentRepository.findByEmail(agent.getEmail()) != null;
        if(agentExists){
            throw new IllegalStateException("Email ime vec postoji!");
        }
        agentExists = agentRepository.findByUsername(agent.getUsername()) != null;
        if(agentExists){
            throw new IllegalStateException("Korisnicko ime agenta vec postoji!");
        }
        return agentRepository.save(agent);
    }

    public Agent login(String username, String password){
        Agent agent = agentRepository.findByUsername(username);
        if(agent == null){
            throw new IllegalStateException("Korisnik ne postoji");
        }
        if(!agent.getPassword().equals(password)){
            throw new IllegalStateException("Lozinka ne postoji");
        }
        String s= generateAPIToken(agent.getId());
        return agent;
    }

    public Agent findByUsername(String username) {
        Agent agent = agentRepository.findByUsername(username);
        if(agent == null){
            throw new IllegalStateException("Agent ne postoji!");
        }
        return agent;
    }

    public Agent findByAgentId(String agentId) {
        Agent agent = agentRepository.findById(agentId);
        if(agent==null)
        {
            throw new IllegalStateException("Agent ne postoji");
        }
        return agent;
    }

    public ArrayList<Agent> findByUsernameContaining(String usernamePart) {
        return agentRepository.findByUsernameContaining(usernamePart);
    }

    //update agent role
    public Agent updateAgentRole(String agentId, String role) {

        Agent agent = agentRepository.findById(agentId);
        if(agent == null)
        {
            throw new IllegalStateException("Agent ne postoji");
        }
        agent.setRole(role);
        if (agentRepository.save(agent) != null) {
            System.out.println("Uspesno izmenjene informacije o ulozi agenta");
            return agent;
        }
        else
            System.out.println("Doslo je do greske, informacije o ulozi agenta nisu uspesno izmenjene!");

        return null;
    }

    public void deleteAllAgents() {
        agentRepository.deleteAll();
    }

    public Boolean deleteAgentById(String agentId) {
        Agent agent = agentRepository.findById(agentId);
        if (agent == null)
            throw new IllegalArgumentException("This agent is not found");
        agentRepository.delete(agent);
        return true;

    }

    public void save(Agent agent) {
        agentRepository.save(agent);
    }

    public Agent setApiToken(String agentId, String apiToken) {
        Agent agent = agentRepository.findById(agentId);
        System.out.println(apiToken);
        System.out.println(agent.getUsername());

        agent.setApiToken(apiToken);
        if (agentRepository.save(agent) != null) {
            System.out.println("Api token set");
            return agent;
        }
        System.out.println("Api token not set");
        return null;
    }
    public String generateAPIToken(String userId) {
        Agent agent = agentRepository.findById(userId);

        if (agent == null) {
            return null;
        }
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 48;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            sb.append(alphaNumeric.charAt(index));
        }
        String token = sb.toString();
        agent.setApiToken(token);
        if (agentRepository.save(agent) != null) {
            return token;
        } else {
            return null;
        }
    }

}