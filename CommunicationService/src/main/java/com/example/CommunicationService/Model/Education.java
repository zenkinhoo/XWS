package com.example.CommunicationService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    @Id
    private String id;
    private String educationalFacility;
    private LocalDate fromDate;
    private LocalDate toDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducationalFacility() {
        return educationalFacility;
    }

    public void setEducationalFacility(String educationalFacility) {
        this.educationalFacility = educationalFacility;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
