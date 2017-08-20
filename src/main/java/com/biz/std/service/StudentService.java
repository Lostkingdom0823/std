package com.biz.std.service;

import com.biz.std.model.CourseOffered;
import com.biz.std.model.CourseSelected;
import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.repository.CourseOfferedRepository;
import com.biz.std.repository.CourseSelectedRepository;
import com.biz.std.repository.GradeRepository;
import com.biz.std.repository.StudentRepository;
import com.biz.std.vo.CourseInfo;
import com.biz.std.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentInfo studentInfo;

    @Autowired
    private CourseOfferedRepository courseOfferedRepository;

    @Autowired
    private CourseSelectedRepository courseSelectedRepository;

    @Autowired
    private CourseInfo courseInfo;

    @Autowired
    private GradeRepository gradeRepository;

    @Transactional
    public boolean insertStudentInfo(Student student){
        //判断是否已有学生的信息
        if(!studentRepository.exists(student.getStudentId())) {
            //新建学生信息导致的班级平均分变化
            Grade grade;
            if(!gradeRepository.exists(student.getStudentGrade().getGradeName())) {
                grade = student.getStudentGrade();
                grade.setNumberOfStudents(1);
                grade.setGradeAvgScore((float)0.0);
            }
            else{
                grade = gradeRepository.findOne(student.getStudentGrade().getGradeName());
                grade.setGradeAvgScore(grade.getNumberOfStudents()*grade.getGradeAvgScore()/(grade.getNumberOfStudents()+1));
                grade.setNumberOfStudents(grade.getNumberOfStudents()+1);
            }

            student.setAvgScore((float)0);
            student.setNumberOfCourses(0);
            Set<Student> students = new HashSet<Student>();
            students.add(student);
            grade.setStudents(students);
            student.setStudentGrade(grade);

            gradeRepository.save(grade);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateStudentInfo(Student student){
        String prefixUrl = "d://JAVA/std/src/main/webapp";
        //判断是否有该学生信息
        if(studentRepository.exists(student.getStudentId())){
            Student temp = studentRepository.findOne(student.getStudentId());
            //图片上传需对原图片删除
            if(student.getStudentImageUrl()!=null) {
                if (temp.getStudentImageUrl()!=null && !temp.getStudentImageUrl().equals("")) {
                    File file = new File(prefixUrl + temp.getStudentImageUrl());
                    file.delete();
                }
            }
            else{
                student.setStudentImageUrl(temp.getStudentImageUrl());
            }

            //班级信息改动导致平均分变化
            Grade oldGrade = gradeRepository.findOne(studentRepository.findStudentGradeByStudentId(temp.getStudentId()));
            if(student.getStudentGrade().getGradeName()!=oldGrade.getGradeName()){
                if(oldGrade.getNumberOfStudents()!=1) {
                    oldGrade.setGradeAvgScore((oldGrade.getNumberOfStudents() * oldGrade.getGradeAvgScore() - temp.getNumberOfCourses() * temp.getAvgScore()) / oldGrade.getNumberOfStudents() - 1);
                    oldGrade.setNumberOfStudents(oldGrade.getNumberOfStudents()-1);
                }
                else{
                    oldGrade.setGradeAvgScore((float)0.0);
                    oldGrade.setNumberOfStudents(0);
                }
            }

            Grade newGrade = new Grade();
            if(gradeRepository.exists(student.getStudentGrade().getGradeName())){
                newGrade = gradeRepository.findOne(student.getStudentGrade().getGradeName());
                newGrade.setGradeAvgScore((newGrade.getNumberOfStudents() * newGrade.getGradeAvgScore() + temp.getNumberOfCourses() * temp.getAvgScore()) / (newGrade.getNumberOfStudents()+1));
                newGrade.setNumberOfStudents(newGrade.getNumberOfStudents()+1);
            }
            else {
                newGrade = student.getStudentGrade();
                newGrade.setGradeAvgScore(temp.getAvgScore() * temp.getNumberOfCourses());
                newGrade.setNumberOfStudents(1);
            }

            gradeRepository.save(oldGrade);

            student.setStudentGrade(newGrade);
            studentRepository.save(student);
            return true;
        }
        else {
            return false;
        }

    }
    // TODO: 2017/8/10 外键引入导致的删除顺序问题，需要解决，删除学生导致班级平均分变化需要解决
    @Transactional
    public void deleteStudentInfo(String studentId){
        Student student = studentRepository.findOne(studentId);
        List<CourseSelected> coursesSelected = courseSelectedRepository.findCourseByStudentId(studentId);
        Grade grade = gradeRepository.findOne(studentRepository.findStudentGradeByStudentId(studentId));

        //学生信息删除导致平均分变化
        if(grade.getNumberOfStudents()!=1) {
            grade.setGradeAvgScore((grade.getNumberOfStudents() * grade.getGradeAvgScore() - student.getNumberOfCourses() * student.getAvgScore()) / grade.getNumberOfStudents() - 1);
            grade.setNumberOfStudents(grade.getNumberOfStudents()-1);
        }
        else{
            grade.setGradeAvgScore((float)0.0);
            grade.setNumberOfStudents(0);
        }

        courseSelectedRepository.delete(coursesSelected);
        studentRepository.delete(studentId);
        gradeRepository.save(grade);
    }

    public ModelAndView getStudentsInfo(Integer contentPage, Integer size){

        List<Student> students = new ArrayList<Student>();
        List<String> grades = new ArrayList<String>();
        if(contentPage==null){
            contentPage=1;
        }
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"avgScore");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Student> page = studentRepository.findAll(pageable);

        for(Student student : page.getContent()){
            grades.add(studentRepository.findStudentGradeByStudentId(student.getStudentId()));
        }

        studentInfo.clear();
        students.addAll(page.getContent());
        studentInfo.setViewName("studentinfo");
        studentInfo.addObject("students",students);
        studentInfo.addObject("grades",grades);
        studentInfo.addObject("contentPage",contentPage);
        studentInfo.addObject("maxPage",page.getTotalPages());
        studentInfo.addObject("totalDetails",(int)page.getTotalElements());

        return studentInfo;
    }


    public ModelAndView getCourseInfo(Integer contentPage, Integer size,String studentId) {

        //标记
        int[] flags = {0,0,0,0,0,0,0,0,0,0};

        if(contentPage == null){
            contentPage = 1;
        }

        //获取当前页课程信息
        List<CourseOffered> courses = new ArrayList<CourseOffered>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"courseName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<CourseOffered> page = courseOfferedRepository.findAll(pageable);
        courses.addAll(page.getContent());

        //获取学生已选课程信息
        List<String> coursesSelected = new ArrayList<String>();
        coursesSelected.addAll(courseSelectedRepository.findCourseSelectedByStudentId(studentId));
        Iterator<CourseOffered> pageIterator = page.iterator();

        //设置标记 PS:感觉好蠢_(:з」∠)_
        for(int i = 0 ; i < 10 ; i++){
            Iterator<String> courseSelectedIterator = coursesSelected.iterator();
            if(pageIterator.hasNext()){
                CourseOffered courseOffered = pageIterator.next();
                while(courseSelectedIterator.hasNext()){
                    if(courseSelectedIterator.next().equals(courseOffered.getCourseName())){
                        flags[i]=1;
                    }
                }
            }
            else {
                break;
            }
        }

        courseInfo.clear();
        courseInfo.setViewName("selectcourse");
        courseInfo.addObject("courseOffered",courses);
        courseInfo.addObject("flags",flags);
        courseInfo.addObject("contentPage",contentPage);
        courseInfo.addObject("maxPage",page.getTotalPages());
        courseInfo.addObject("totalDetails",(int)page.getTotalElements());
        courseInfo.addObject("studentId",studentId);

        return courseInfo;
    }

    // TODO: 2017/8/15 待添加选课/退课时修改该课人数
    @Transactional
    public boolean selectCourse(String studentId, String courseName) {
        Student student = studentRepository.findOne(studentId);
        CourseOffered courseOffered = courseOfferedRepository.findOne(courseName);

        //该课平均分变化
        courseOffered.setAvgScore(courseOffered.getAvgScore()*courseOffered.getNumberOfStudents()/(courseOffered.getNumberOfStudents()+1));

        //课程人数变化
        courseOffered.setNumberOfStudents(courseOffered.getNumberOfStudents()+1);

        //初始化选课信息
        CourseSelected courseSelected = new CourseSelected();
        courseSelected.setCourseId(studentId+courseName);
        courseSelected.setCourseName(courseName);
        courseSelected.setStudent(student);
        courseSelected.setScore((float)0.0);

        //修改学生平均分
        student.setAvgScore(student.getAvgScore()*student.getNumberOfCourses()/(student.getNumberOfCourses()+1));

        //修改学生课程数
        student.setNumberOfCourses(student.getNumberOfCourses()+1);

        //设置外键
        Set<CourseSelected> selectedSet = new HashSet<CourseSelected>();
        selectedSet.add(courseSelected);
        student.setCourses(selectedSet);

        courseOfferedRepository.save(courseOffered);
        studentRepository.save(student);
        return true;
    }

    // TODO: 2017/8/15 退课涉及的分数改动待完成，退课涉及的课程人数变化待完成
    @Transactional
    public boolean abandonCourse(String studentId, String courseName){

        CourseSelected courseSelected = courseSelectedRepository.findOne(studentId+courseName);
        CourseOffered courseOffered = courseOfferedRepository.findOne(courseName);
        Student student = studentRepository.findOne(studentId);
        Grade grade = gradeRepository.findOne(studentRepository.findStudentGradeByStudentId(studentId));

        //退课涉及的该课平均分变化
        if(courseOffered.getNumberOfStudents()!=1){
            courseOffered.setAvgScore((courseOffered.getAvgScore() * courseOffered.getNumberOfStudents()-courseSelected.getScore())/courseOffered.getNumberOfStudents()-1);
            courseOffered.setNumberOfStudents(courseOffered.getNumberOfStudents() - 1);
        }
        else {
            courseOffered.setAvgScore((float)0);
            courseOffered.setNumberOfStudents(0);
        }

        //学生平均分变化及课程数变化
        if(student.getNumberOfCourses()!=1) {
            student.setAvgScore((student.getAvgScore() * student.getNumberOfCourses() - courseSelected.getScore()) / (student.getNumberOfCourses() - 1));
            student.setNumberOfCourses(student.getNumberOfCourses() - 1);
        }
        else {
            student.setAvgScore((float)0.0);
            student.setNumberOfCourses(0);
        }
        //班级平均分变化
        grade.setGradeAvgScore((grade.getGradeAvgScore() * grade.getNumberOfStudents() - courseSelected.getScore())/grade.getNumberOfStudents());

        gradeRepository.save(grade);
        studentRepository.save(student);
        courseOfferedRepository.save(courseOffered);

        //删除选课信息
        courseSelectedRepository.delete(studentId+courseName);

        return true;
    }

    public ModelAndView getStudentScoreInfo(String studentId) {

        List<CourseSelected> courseSelectedList = new ArrayList<CourseSelected>();

        courseSelectedList.addAll(courseSelectedRepository.findCourseByStudentId(studentId));

        studentInfo.clear();
        studentInfo.setViewName("scoreinfo");
        studentInfo.addObject("courses",courseSelectedList);
        studentInfo.addObject("studentId",studentId);
        return studentInfo;
    }

    @Transactional
    public boolean updateStudentScoreInfo(String studentId,String courseName,Float courseScore){

        if(courseScore != null) {

            CourseOffered courseOffered = courseOfferedRepository.findOne(courseName);
            CourseSelected courseSelected = courseSelectedRepository.findOne(studentId + courseName);
            Student student = studentRepository.findOne(studentId);
            Grade grade = gradeRepository.findOne(studentRepository.findStudentGradeByStudentId(studentId));

            Float oldScore = courseSelected.getScore();
            if(oldScore != courseScore) {

                if (student.getAvgScore() == null) {
                    student.setAvgScore((float) 0.0);
                }

                String[] avgScoreAndCount = courseSelectedRepository.getAvgScoresAndCount(studentId).split(",");

                courseSelected.setStudent(student);
                courseSelected.setCourseId(studentId + courseName);
                courseSelected.setScore(courseScore);
                courseSelected.setCourseName(courseName);

                //计算学生平均分
                Float scoreChange = courseScore - oldScore;
                Float avgScoreNow = (scoreChange / Float.valueOf(avgScoreAndCount[1])) + student.getAvgScore();
                Float avgScoreChange = avgScoreNow - student.getAvgScore();
                student.setAvgScore(avgScoreNow);

                //计算班级平均分
                Float gradeAvgScore = ((grade.getGradeAvgScore()*grade.getNumberOfStudents()+scoreChange) / grade.getNumberOfStudents());
                grade.setGradeAvgScore(gradeAvgScore);

                //该课平均分变化
                courseOffered.setAvgScore((courseOffered.getAvgScore() * courseOffered.getNumberOfStudents()+scoreChange)/courseOffered.getNumberOfStudents());

                courseOfferedRepository.save(courseOffered);
                courseSelectedRepository.save(courseSelected);
                studentRepository.save(student);
                gradeRepository.save(grade);

                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
