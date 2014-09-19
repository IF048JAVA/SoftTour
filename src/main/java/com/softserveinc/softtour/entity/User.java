package com.softserveinc.softtour.entity;

import com.softserveinc.softtour.entity.template.Sex;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 10)
    private String password;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "age", nullable = false, length = 3)
    private byte age;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "phone", length = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

/*    @OneToMany(mappedBy = "user")
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private Set<HistoryRecord> historyRecords;

    @OneToMany(mappedBy = "user")
    private Set<HistoryRequest> historyRequests;*/

    public User() {
    }

    public User(String name, String email,
                String password, Date birthday,
                byte age, Sex sex, String phone, Role role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

 /*   public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    public void setHistoryRecords(Set<HistoryRecord> historyRecords) {
        this.historyRecords = historyRecords;
    }

    public Set<HistoryRequest> getHistoryRequests() {
        return historyRequests;
    }

    public void setHistoryRequests(Set<HistoryRequest> historyRequests) {
        this.historyRequests = historyRequests;
    }
*/
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

