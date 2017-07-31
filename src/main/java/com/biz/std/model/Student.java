package com.biz.std.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="student")
public class Student {
		
	private Integer id;
	private String studentId;
	private String studentName;
	private String studentSex;
	private Date studentBirthday;
	private String studentClass;
	private Integer studentSujectsInLearning;
	private Float studentAvgScore;
	//private Image studentImage;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=10)
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
	public Float getStudentAvgScore() {
		return studentAvgScore;
	}
	public void setStudentAvgScore(float studentAvgScore) {
		this.studentAvgScore = studentAvgScore;
	}
	@Column(length=40,nullable=false)
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
}
