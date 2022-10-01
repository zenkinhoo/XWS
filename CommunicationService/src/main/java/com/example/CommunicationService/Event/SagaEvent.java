package com.example.CommunicationService.Event;

import java.util.Date;
import java.util.UUID;

public class SagaEvent {
    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();

    public UUID getEventId() {
        return eventId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
