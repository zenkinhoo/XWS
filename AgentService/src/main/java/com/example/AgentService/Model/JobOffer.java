package com.example.AgentService.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "offers")
public class JobOffer {
    @Id
    private String id;
    private String position;
    private String description;
    private String location;
    private ArrayList<Requirement> requirements = new ArrayList<>();
    private ArrayList<DailyActivity> daily_activities = new ArrayList<>();
    private String companyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }

    public ArrayList<DailyActivity> getDaily_activities() {
        return daily_activities;
    }

    public void setDaily_activities(ArrayList<DailyActivity> daily_activities) {
        this.daily_activities = daily_activities;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
