package com.handoferis.models;

import org.hibernate.id.GUIDGenerator;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

public class RehearsalJam {

    private UUID Id = UUID.randomUUID();
    private String Title;
    private String Track;

    public RehearsalJam(String title, String track) {
        Title = title;
        Track = track;
    }

    public UUID getId() {
        return Id;
    }

    protected void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTrack() {
        return Track;
    }

    public void setTrack(String track) {
        Track = track;
    }

    @Override
    public String toString() {
        return "RehearsalJam{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", Track='" + Track + '\'' +
                '}';
    }
}
