package com.softserveinc.softtour.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HistoryRecords")
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    public HistoryRecord() {
    }

    public HistoryRecord(Date date, User user, Tour tour) {
        this.date = date;
        this.user = user;
        this.tour = tour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "HistoryRecord{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", tour=" + tour +
                '}';
    }
}
