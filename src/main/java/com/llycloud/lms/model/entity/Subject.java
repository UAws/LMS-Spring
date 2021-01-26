package com.llycloud.lms.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {
    private Integer subjectId;
    private String name;

    @ToString.Exclude
    private Set<People> containedPeople = new HashSet<People>();

    @ManyToMany(
            targetEntity = People.class,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "people_subject",
            joinColumns = {@JoinColumn(name = "subject_id",referencedColumnName = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")}
    )
    @JsonBackReference
    public Set<People> getContainedPeople() {
        return containedPeople;
    }

    public void setContainedPeople(Set<People> containedPeople) {
        this.containedPeople = containedPeople;
    }

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId) && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, name);
    }
}
