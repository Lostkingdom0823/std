package com.biz.std.service;

import com.biz.std.model.CourseSelected;
import com.biz.std.model.Grade;
import com.biz.std.repository.CourseSelectedRepository;
import com.biz.std.repository.GradeRepository;
import com.biz.std.repository.StudentRepository;
import com.biz.std.vo.GradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseSelectedRepository courseSelectedRepository;

    @Autowired
    private GradeInfo gradeInfo;

    @Transactional
    public boolean insertGradeInfo(Grade grade){
        if(!gradeRepository.exists(grade.getGradeName())) {
            grade.setGradeAvgScore((float)0.0);
            grade.setNumberOfStudents(0);
            gradeRepository.save(grade);
            return true;
        }
        else{
            return false;
        }
    }

    @Transactional
    public void deleteGradeInfo(Grade grade){

        List<String> studentIds = studentRepository.findStudentIdByGradeName(grade.getGradeName());
        Iterator<String> studentIdsIter = studentIds.iterator();

        while (studentIdsIter.hasNext()){
            String studentId = studentIdsIter.next();
            List<CourseSelected> courseSelectedList = courseSelectedRepository.findCourseByStudentId(studentId);
            courseSelectedRepository.delete(courseSelectedList);
            studentRepository.delete(studentId);
        }

        gradeRepository.delete(grade.getGradeName());

    }

    public ModelAndView getGradesInfo(Integer contentPage, int size) {

        if(contentPage==null){
            contentPage=1;
        }

        List<Grade> grades = new ArrayList<Grade>();

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"gradeName");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(contentPage-1,size,sort);
        Page<Grade> page = gradeRepository.findAll(pageable);

        grades.addAll(page.getContent());

        gradeInfo.clear();
        gradeInfo.setViewName("gradeinfo");
        gradeInfo.addObject("grades",grades);
        gradeInfo.addObject("contentPage",contentPage);
        gradeInfo.addObject("maxPage",page.getTotalPages());
        gradeInfo.addObject("totalDetails",(int)page.getTotalElements());

        return gradeInfo;
    }
}
