package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {

    private String className;
    private Integer numberOfStudents;
    private Float classAvgScore;

    @Id
    @Column(length = 40)
    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(columnDefinition = "int default 0 not null")
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    @Column(columnDefinition = "float default 0.0 not null")
    public Float getClassAvgScore() {
        return classAvgScore;
    }

    public void setClassAvgScore(Float classAvgScore) {
        this.classAvgScore = classAvgScore;
    }

    public Grade(){

    }

    public Grade(String className, Integer numberOfStudents, Float classAvgScore){
        this.className = className;
        this.numberOfStudents = numberOfStudents;
        this.classAvgScore = classAvgScore;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "className='" + className + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", classAvgScore=" + classAvgScore +
                '}';
    }
}
