package com.biz.std.model;

import javax.persistence.*;

@Entity
@Table(name="courseSelected")
public class CourseSelected {

    private String CourseId;
    private String CourseName;
    private Float score;
    private Student student;

    //format:studentId+CourseSelectedName
    @Id
    @Column(length = 40)
    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String CourseSelectedId) {
        this.CourseId = CourseSelectedId;
    }

    @Column(length = 40,nullable = false)
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseSelectedName) {
        this.CourseName = CourseSelectedName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studentId")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(columnDefinition = "float default 0.0")
    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public CourseSelected(){

    }
}
