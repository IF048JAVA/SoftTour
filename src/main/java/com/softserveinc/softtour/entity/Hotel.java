package com.softserveinc.softtour.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "stars", nullable = false, length = 1)
    private int Stars;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

/*    @OneToMany(mappedBy = "hotel")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "hotel")
    private Set<Tour> tours;*/

    public Hotel() {
    }

    public Hotel(String name, int stars, Region region) {
        this.name = name;
        Stars = stars;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return Stars;
    }

    public void setStars(int stars) {
        Stars = stars;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

/*    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }*/

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Stars=" + Stars +
                ", region=" + region.getName() +
                '}';
    }
}
