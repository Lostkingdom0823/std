package com.biz.std.service;

import com.biz.std.model.CourseOffered;
import com.biz.std.model.CourseSelected;
import com.biz.std.model.Student;
import com.biz.std.repository.CourseOfferedRepository;
import com.biz.std.repository.CourseSelectedRepository;
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

    @Transactional
    public void deleteStudentInfo(String studentId){
        studentRepository.delete(studentId);
    }

    public ModelAndView getStudentsInfo(Integer contentPage, Integer size){
        List<Student> students = new ArrayList<Student>();
        if(contentPage==null){
            contentPage=1;
        }
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"avgScore");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Student> page = studentRepository.findAll(pageable);

        studentInfo.clear();
        students.addAll(page.getContent());
        studentInfo.setViewName("studentinfo");
        studentInfo.addObject("students",students);
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
        System.out.println(courses.size());
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

    public ModelAndView selectCourse(String studentId, String courseName) {
        Student student = studentRepository.findOne(studentId);
        CourseSelected courseSelected = new CourseSelected();
        courseSelected.setCourseId(studentId+courseName);
        courseSelected.setCourseName(courseName);
        courseSelected.setStudent(student);
        Set<CourseSelected> selectedSet = new HashSet<CourseSelected>();
        selectedSet.add(courseSelected);
        student.setCourses(selectedSet);
        studentRepository.save(student);
        return null;
    }
}
