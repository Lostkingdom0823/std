package com.biz.std.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="student")
public class Student {

<<<<<<< HEAD
        private String studentId;
        private String studentName;
        private String studentClass;
        private Date studentBirthday;
        private String studentSex;
        private String subjectsDetail;
        private Float studentAvgScore;
        private String studentImageUrl;
=======
	private String studentId;
	private String studentName;
	private String studentSex;
	private Date studentBirthday;
	private String studentClass;
	private String Sujects;
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
	public String getSujects() {
		return Sujects;
	}
	public void setSujects(String Sujects) {
		this.Sujects = Sujects;
	}
	@Column(columnDefinition = "float default 0.0")
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
>>>>>>> cc7ebf9cfcf9d9004ba74998078b7d74ec26c37d

        @Id
        @Column(length = 40)
        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }
        @Column(length = 40 , nullable = false)
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

<<<<<<< HEAD
        public void setStudentImageUrl(String studentImageUrl) {
            this.studentImageUrl = studentImageUrl;
        }
    }

=======
    public  Student(String studentId,String studentName,String studentClass,Date studentBirthday,String studentSex,String Sujects,Float studentAvgScore,String studentImageUrl){
	    this.studentId = studentId;
	    this.studentName = studentName;
	    this.studentClass = studentClass;
	    this.studentBirthday = studentBirthday;
	    this.studentSex = studentSex;
	    this.Sujects = Sujects;
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
				", studentSujectsInLearning=" + Sujects +
				", studentAvgScore=" + studentAvgScore +
				", studentImageUrl='" + studentImageUrl + '\'' +
				'}';
	}
}
>>>>>>> cc7ebf9cfcf9d9004ba74998078b7d74ec26c37d
