package com.handoferis.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class UploadJam {

    @Id
    private String id;
    private String title;
    private Date jamDate;
    private Date uploadDate;
    private String user;
    private String blobURL;
    private String notes;

    public UploadJam(String title, Date jamDate, Date uploadDate, String user, String blobURL, String notes) {
        this.title = title;
        this.jamDate = jamDate;
        this.uploadDate = uploadDate;
        this.user = user;
        this.blobURL = blobURL;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getJamDate() {
        return jamDate;
    }

    public void setJamDate(Date jamDate) {
        this.jamDate = jamDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBlobURL() {
        return blobURL;
    }

    public void setBlobURL(String blobURL) {
        this.blobURL = blobURL;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "UploadJam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", jamDate=" + jamDate +
                ", uploadDate=" + uploadDate +
                ", user='" + user + '\'' +
                ", blobURL='" + blobURL + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
