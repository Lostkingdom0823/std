package com.biz.std.model;

public class Student {
	private String studentId;
	private String studentName;
	private String studentSex;
	private String studentBirthday;
	private String studentClass;
	private String studentSujectsInLearning;
	private float studentAvgScore;
	
	
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public String getStudentSujectsInLearning() {
		return studentSujectsInLearning;
	}
	public void setStudentSujectsInLearning(String studentSujectsInLearning) {
		this.studentSujectsInLearning = studentSujectsInLearning;
	}
	public float getStudentAvgScore() {
		return studentAvgScore;
	}
	public void setStudentAvgScore(float studentAvgScore) {
		this.studentAvgScore = studentAvgScore;
	}
	//private Image studentImage;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(String studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	
	
}
