package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courseOffered")
public class CourseOffered {

    private String courseName;
    private Integer numberOfStudents;
    private Float avgScore;

    @Id
    @Column(length = 40)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(columnDefinition = "int default 0")
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Column(columnDefinition = "float default 0.0")
    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

    public CourseOffered(){

    }
}
