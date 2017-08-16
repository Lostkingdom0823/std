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
    public void insertStudentInfo(Student student){
        studentRepository.save(student);
    }

    @Transactional
    public boolean updateStudentInfo(Student student){
        String prefixUrl = "d://JAVA/std/src/main/webapp";
        if(studentRepository.exists(student.getStudentId())){
            Student temp = studentRepository.findOne(student.getStudentId());
            if(student.getStudentImageUrl()!=null) {
                if (temp.getStudentImageUrl()!=null && !temp.getStudentImageUrl().equals("")) {
                    File file = new File(prefixUrl + temp.getStudentImageUrl());
                    file.delete();
                }
            }
            else{
                student.setStudentImageUrl(temp.getStudentImageUrl());
            }
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
        studentRepository.delete(studentId);
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
        coursesSelected.addAll(studentRepository.findCourseSelectedByStudentId(studentId));
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

        //初始化选课信息
        CourseSelected courseSelected = new CourseSelected();
        courseSelected.setCourseId(studentId+courseName);
        courseSelected.setCourseName(courseName);
        courseSelected.setStudent(student);
        courseSelected.setScore((float)0.0);

        //修改学生课程数
        Integer numberOfCoursesBefore = courseSelectedRepository.getNumberOfCoursesByStudentId(studentId);
        student.setNumberOfCourses(numberOfCoursesBefore+1);

        //修改学生平均分
        student.setAvgScore(student.getAvgScore()*numberOfCoursesBefore/(numberOfCoursesBefore+1));

        //设置外键
        Set<CourseSelected> selectedSet = new HashSet<CourseSelected>();
        selectedSet.add(courseSelected);
        student.setCourses(selectedSet);
        studentRepository.save(student);
        return true;
    }

    // TODO: 2017/8/15 退课涉及的分数改动待完成，退课涉及的课程人数变化待完成
    @Transactional
    public boolean abandonCourse(String studentId, String courseName){

        //退课涉及的平均分改动
        //学生平均分变化
        CourseSelected courseSelected = courseSelectedRepository.findOne(studentId+courseName);
        CourseOffered courseOffered = courseOfferedRepository.findOne(courseName);
        courseOffered.setNumberOfStudents(courseOffered.getNumberOfStudents() - 1);
        Student student = studentRepository.findOne(studentId);
        Float studentAvgScoreBefore = student.getAvgScore();
        student.setAvgScore((student.getAvgScore() * student.getNumberOfCourses()-courseSelected.getScore())/(student.getNumberOfCourses()-1));

        //班级平均分变化
        String gradeName = studentRepository.findStudentGradeByStudentId(studentId);
        List<Float> avgScores = studentRepository.findAvgScoreByGradeName(gradeName);
        Grade grade = gradeRepository.findOne(gradeName);
        Float totalScore = student.getAvgScore()-studentAvgScoreBefore;
        for(Float score : avgScores){
            totalScore+=score;
        }
        grade.setGradeAvgScore(totalScore/avgScores.size());
        gradeRepository.save(grade);


        //课程人数改动
        student.setNumberOfCourses(student.getNumberOfCourses()-1);
        studentRepository.save(student);

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

            CourseSelected courseSelected = courseSelectedRepository.findOne(studentId + courseName);
            Float oldScore = courseSelected.getScore();

            if(oldScore != courseScore) {

                Student student = studentRepository.findOne(studentId);
                String studentGradeName = studentRepository.findStudentGradeByStudentId(studentId);
                Grade grade = gradeRepository.findOne(studentGradeName);
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
                student.setStudentGrade(grade);

                //计算班级平均分
                Float gradeAvgScore = (avgScoreChange / studentRepository.getNumberOfStudentByGradeName(studentGradeName)) + grade.getGradeAvgScore();
                grade.setGradeAvgScore(gradeAvgScore);

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
