package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject {

    private String subjectName;
    private Integer numberOfStudents;
    private Float subjectAvgScore;

    @Id
    @Column(length = 40)
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    @Column(columnDefinition = "int default 0 notnull")
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    @Column(columnDefinition = "float default 0.0 notnull")
    public Float getSubjectAvgScore() {
        return subjectAvgScore;
    }

    public void setSubjectAvgScore(Float subjectAvgScore) {
        this.subjectAvgScore = subjectAvgScore;
    }

    public Subject(){

    }

    public Subject(String subjectName,Integer numberOfStudents,Float subjectAvgScore){
        this.numberOfStudents = numberOfStudents;
        this.subjectAvgScore = subjectAvgScore;
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", subjectAvgScore=" + subjectAvgScore +
                '}';
    }
}
