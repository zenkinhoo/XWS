package com.example.CommunicationService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document(collection = "notification")
public class Notification {

    @Id
    private String id;

    private String text;

    private LocalDateTime creationTime;

    private Boolean read;

    public Notification()
    {
        this.creationTime =  LocalDateTime.now();
        this.read = false;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime() {
        this.creationTime =  LocalDateTime.now();

    }
}
