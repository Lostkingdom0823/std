package com.biz.std.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {

	private String studentId;
	private String studentName;
	private String studentSex;
	private Date studentBirthday;
	private String studentClass;
	private Integer studentSujectsInLearning;
	private Float studentAvgScore;
	private String studentImageUrl;

	@Column(length=40)
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	@Column(length=5)
	public Integer getStudentSujectsInLearning() {
		return studentSujectsInLearning;
	}
	public void setStudentSujectsInLearning(Integer studentSujectsInLearning) {
		this.studentSujectsInLearning = studentSujectsInLearning;
	}
	@Column(nullable = false,columnDefinition = "float default 0.0")
	public Float getStudentAvgScore() {
		return studentAvgScore;
	}
	public void setStudentAvgScore(Float studentAvgScore) {
		this.studentAvgScore = studentAvgScore;
	}
	@Id
	@Column(length=40)
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	@Column(length=40,nullable=false)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Column(length=10)
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}

	@Column(length = 100)
	public String getStudentImageUrl() {
		return studentImageUrl;
	}

	public void setStudentImageUrl(String studentImageUrl) {
		this.studentImageUrl = studentImageUrl;
	}

	public Student(){

    }

    public  Student(String studentId,String studentName,String studentClass,Date studentBirthday,String studentSex,Integer studentSujectsInLearning,Float studentAvgScore,String studentImageUrl){
	    this.studentId = studentId;
	    this.studentName = studentName;
	    this.studentClass = studentClass;
	    this.studentBirthday = studentBirthday;
	    this.studentSex = studentSex;
	    this.studentSujectsInLearning = studentSujectsInLearning;
	    this.studentAvgScore = studentAvgScore;
	    this.studentImageUrl = studentImageUrl;
    }

	@Override
	public String toString() {
		return "Student{" +
				"studentId='" + studentId + '\'' +
				", studentName='" + studentName + '\'' +
				", studentSex='" + studentSex + '\'' +
				", studentBirthday=" + studentBirthday +
				", studentClass='" + studentClass + '\'' +
				", studentSujectsInLearning=" + studentSujectsInLearning +
				", studentAvgScore=" + studentAvgScore +
				", studentImageUrl='" + studentImageUrl + '\'' +
				'}';
	}
}
