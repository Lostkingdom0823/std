package com.biz.std.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grade")
public class Grade {

    private String gradeName;
    private Integer numberOfStudents;
    private Float gradeAvgScore;
    private Set<Student> students;

    @Id
    @Column(length = 20)
    public String getGradeName() {

        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Column(columnDefinition = "int default 0")
    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    @Column(columnDefinition = "float default 0.0")
    public Float getGradeAvgScore() {
        return gradeAvgScore;
    }

    public void setGradeAvgScore(Float gradeAvgScore) {
        this.gradeAvgScore = gradeAvgScore;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "studentGrade")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Grade(){

    }
}
