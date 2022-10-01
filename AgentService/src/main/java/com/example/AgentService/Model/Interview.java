package com.example.AgentService.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "interviews")
public class Interview {
    @Id
    private String id;
    private String hr_part;
    private String technical_part;
    private String rejection_reason; // optional part
    private String commentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHr_part() {
        return hr_part;
    }

    public void setHr_part(String hr_part) {
        this.hr_part = hr_part;
    }

    public String getTechnical_part() {
        return technical_part;
    }

    public void setTechnical_part(String technical_part) {
        this.technical_part = technical_part;
    }

    public String getRejection_reason() {
        return rejection_reason;
    }

    public void setRejection_reason(String rejection_reason) {
        this.rejection_reason = rejection_reason;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
