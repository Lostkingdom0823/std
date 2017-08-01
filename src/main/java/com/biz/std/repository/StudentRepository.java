package com.biz.std.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.biz.std.model.Student;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = Student.class,idClass = String.class)
public interface StudentRepository {//extends Repository<Student, Integer>
	
	public Student findByStudentName(String studentName);

    @Query("select o from Student o where o.studentName=?1 and o.studentId=?2")
    public List<Student> getStudentsByNameAndId(String studentName,String studentId);

    @Modifying
    @Query("update Student o set o.studentName = :studentName," +
            "o.studentClass = :studentClass," +
            "o.studentBirthday = :studentBirthday," +
            "o.studentSex = :studentSex," +
            "o.studentAvgScore = :studentAvgScore," +
            "o.studentSujectsInLearning = :studentSubjectsInLearning where o.studentId=:studentId")
    public void updateStudentInfo( @Param("studentId")String studentId,
                                    @Param("studentName") String studentName,
                                    @Param("studentClass") String studentClass,
                                    @Param("studentBirthday")Date studentBirthday,
                                    @Param("studentSex")String studentSex,
                                    @Param("studentSubjectsInLearning")Integer studentSujectsInLearning,
                                    @Param("studentAvgScore")Float studentAvgScore);
}
