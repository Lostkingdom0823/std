package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="student")
public class Student {
	private String studentId;
	private String studentName;
	private String studentClass;
	private Date studentBirthday;
	private String studentSex;
	private String subjectsDetail;
	private Float studentAvgScore;
	private String studentImageUrl;

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

	@Column(length = 20)
	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	@Column
	public String getSubjectsDetail() {
		return subjectsDetail;
	}

	public void setSubjectsDetail(String subjectsDetail) {
		this.subjectsDetail = subjectsDetail;
	}

	@Column(columnDefinition = "float default 0.0")
	public Float getStudentAvgScore() {
		return studentAvgScore;
	}

	public void setStudentAvgScore(Float studentAvgScore) {
		this.studentAvgScore = studentAvgScore;
	}

	@Column(length = 100)
	public String getStudentImageUrl() {
		return studentImageUrl;
	}

    public void setStudentImageUrl(String studentImageUrl) {
        this.studentImageUrl = studentImageUrl;
    }
}