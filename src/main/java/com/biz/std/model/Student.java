package com.biz.std.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="student")
public class Student {
	private String studentId;
	private String studentName;
	private Grade studentGrade;
	private Date studentBirthday;
	private String studentSex;
	private Float avgScore;
	private String studentImageUrl;
	private Set<CourseSelected> Courses;

	@Id
	@Column(length = 40)
	public String getStudentId () {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(length = 40, nullable = false)
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(length = 10)
	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	@Column
	public Date getStudentBirthday() {
		return studentBirthday;
	}

	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gradeName")
	public Grade getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(Grade studentGrade) {
		this.studentGrade = studentGrade;
	}

	@Column(length = 100)
	public String getStudentImageUrl() {
		return studentImageUrl;
	}

    public void setStudentImageUrl(String studentImageUrl) {
        this.studentImageUrl = studentImageUrl;
    }

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
	public Set<CourseSelected> getCourses() {
		return Courses;
	}

	public void setCourses(Set<CourseSelected> courses) {
		Courses = courses;
	}
	
	@Column(columnDefinition = "float default 0.0")
    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }
}