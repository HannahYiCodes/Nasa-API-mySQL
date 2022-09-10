package com.careerdevs.nasa.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name="Data")
public class NasaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;
    private String date;
    private String explanation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hdurl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String copyright;
    private String media_type;
    private String service_version;
    private String title;
    private String url;


    public void removeId() { id = 0; }
    public int getId() { return id; }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
