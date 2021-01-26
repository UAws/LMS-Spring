package com.llycloud.lms.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Akide Liu
 * @date 2021-01-16 18:47
 */
@Entity
@ToString
@Table(name = "people")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People {

    private Integer userId;
    private String name;
    private String password;
    private String title;
    private Boolean isActive;
    private Integer userLevel;

    // https://stackoverflow.com/a/23973497/14207562
    @ToString.Exclude
    private Set<Subject> belongedSubjects = new HashSet<Subject>();

    @ManyToMany(
            targetEntity = Subject.class,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "people_subject",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "subject_id")}
    )
    @JsonManagedReference
    public Set<Subject> getBelongedSubjects() {
        return belongedSubjects;
    }

    public void setBelongedSubjects(Set<Subject> belongedSubjects) {
        this.belongedSubjects = belongedSubjects;
    }

    @Id
    @Column(name = "user_id")

    /*
     * https://stackoverflow.com/a/37252037/14207562
     * The @GeneratedValue annotation is to configure the way of increment of the specified column(field).
     * For example when using Mysql, you may specify auto_increment in the definition of table to make it self-incremental, and then use
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserId() {

        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "user_level")

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(userId, people.userId) && Objects.equals(name, people.name) && Objects.equals(password, people.password) && Objects.equals(title, people.title) && Objects.equals(isActive, people.isActive) && Objects.equals(userLevel, people.userLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, title, isActive, userLevel);
    }

}
