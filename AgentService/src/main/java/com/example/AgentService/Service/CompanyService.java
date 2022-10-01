package com.example.AgentService.Service;


import com.example.AgentService.Model.Agent;
import com.example.AgentService.Model.Company;
import com.example.AgentService.Repository.AgentRepository;
import com.example.AgentService.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired

    private AgentRepository agentRepository;
    public Company registerCompany(String ownerId,Company company) {

        Company c = companyRepository.findByName(company.getName());
        if (c!=null) {
            System.out.println("Kompanija sa datim imenom vec postoji");
            return null;
        }
        company.setApproved(false);
        company.setOwnerId(ownerId);
        if (companyRepository.save(company) != null) {
            System.out.println("Zahtev za registraciju kompanije je uspesno poslat, ceka se da je admin odobri.");

            return companyRepository.save(company);
        }
        System.out.println("Kompanija nije uspesno sacuvana.");
        return null;
    }
    public void save(Company company) {
        companyRepository.save(company);
    }

    public ArrayList<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public ArrayList<Company> getCompaniesOfSpecificOwner(String ownerId) {
        ArrayList<Company> companies = companyRepository.findByOwnerId(ownerId);
        if(companies==null)
        {
            throw new IllegalStateException("Ovaj korisnik nema kompanija");
        }
        return companies;
    }

    public ArrayList<Company> getUnapprovedCompanies() {
        return companyRepository.findByApproved(false);
    }
    public Boolean approveCompanyRegistration(String adminId,String companyId) {
        Agent admin = agentRepository.findById(adminId);
        if(!admin.getRole().equals("Admin"))
        {
            System.out.println("Niko sem admina ne moze odobriti registraciju kompanije!");
            return false;
        }
        Company company = companyRepository.findById(companyId);
        if (company == null) {
            System.out.println("Nije pronadjena kompanija");
            return false;
        }
        if (company.getApproved()) {
            System.out.println("Vec je odobrena registracija ove kompanije");
            return false;
        }
        company.setRequest("false");
        company.setApproved(true);
        if (companyRepository.save(company) != null) {
            System.out.println("Registracija firme je odobrena");
            Agent owner = agentRepository.findById(company.getOwnerId());
            System.out.println(owner.getUsername());
            owner.setRole("Owner");
            agentRepository.save(owner);
            return true;
        }
        System.out.println("Doslo je do greske, registracija firme nije odobrena odobrena");
        return false;
    }
    public Company updateCompany(String companyId, Company company) {
        Company c = companyRepository.findById(companyId);

        Agent agent = agentRepository.findById(c.getOwnerId());
        if(agent == null)
        {
            throw new IllegalStateException("Agent ne postoji");
        }
        if (!agent.getRole().equals("Owner")){
            throw new IllegalStateException("Agent koji nije vlasnik ne sme da menja sadrzaj kompanije");
        }

        c.setEmail(company.getEmail());
        c.setDescription(company.getDescription());
        if(!companyNameExists(company.getName()))
        {
            c.setName(company.getName());
        }
        c.setAddress(company.getAddress());
        c.setMobile(company.getMobile());
        c.setProfilePicture(company.getProfilePicture());
        if (companyRepository.save(c) != null) {
            System.out.println("Uspesno izmenjene informacije o kompaniji");
            return c;
        }
        System.out.println("Doslo je do greske, informaciji o kompaniji nisu uspesno izmenjene!");
        return null;
    }
    public Boolean companyNameExists(String companyName)
    {
        ArrayList<Company> companies = companyRepository.findAll();
        for (Company c:companies
             ) {
            if (c.getName().equals(companyName))
            {
                return true;
            }
        }
        return false;
    }

    public Company setApiToken(String companyId, String apiToken) {
        Company company = companyRepository.findById(companyId);
        if (company != null) {
            company.setApiToken(apiToken);
            companyRepository.save(company);
            return company;
        }
        return null;
    }
}
