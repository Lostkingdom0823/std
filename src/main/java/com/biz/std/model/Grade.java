package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {

    private String className;
    private Integer studentsNumber;
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
    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
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

    public Grade(String className,Integer studentsNumber,Float classAvgScore){
        this.className = className;
        this.studentsNumber = studentsNumber;
        this.classAvgScore = classAvgScore;
    }
}
