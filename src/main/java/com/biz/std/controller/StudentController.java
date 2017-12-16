package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
import com.biz.std.vo.StudentInfo;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

    @RequestMapping(value="/insert.do")
	public String doInsert(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String studentImageUrl = null;
        Map<String,String> studentInfo = new HashMap<String, String>();
        if(ServletFileUpload.isMultipartContent(request)){
            ServletFileUpload upload = new ServletFileUpload();
            try {
                putImage(upload,request,studentInfo,studentImageUrl);

                Student student = new Student();
                Grade grade = new Grade();
                grade.setGradeName(studentInfo.get("studentClass"));
                student.setStudentId(studentInfo.get("studentId"));
                student.setStudentName(studentInfo.get("studentName"));
                student.setStudentGrade(grade);
                if(!studentInfo.get("studentBirthday").equals("")) {
                    String[] time = studentInfo.get("studentBirthday").split("-");
                    student.setStudentBirthday(new java.sql.Date(Integer.parseInt(time[0]) - 1900, Integer.parseInt(time[1]) - 1, Integer.parseInt(time[2])));
                }
                student.setStudentSex(studentInfo.get("studentSex"));
                student.setStudentImageUrl(studentImageUrl);
                if(studentService.insertStudentInfo(student)){
                    return "redirect:/student/getinfo.do";
                }
                return "error";
            }catch (Exception e){
                e.printStackTrace();
                return "error";
            }
        }
        else {
            return "error";
        }
	}

	@RequestMapping("/update.do")
	public String doUpdate(HttpServletRequest request) throws IOException, FileUploadException {
		request.setCharacterEncoding("UTF-8");
		String studentImageUrl = null;
        Map<String,String> studentInfo = new HashMap<String, String>();
        if(ServletFileUpload.isMultipartContent(request)){
            ServletFileUpload upload = new ServletFileUpload();
            try {
                studentImageUrl = putImage(upload,request,studentInfo,studentImageUrl);

                Student student = new Student();
                Grade grade = new Grade();
                grade.setGradeName(studentInfo.get("studentClass"));
                student.setStudentId(studentInfo.get("studentId"));
                student.setStudentName(studentInfo.get("studentName"));
                student.setStudentGrade(grade);
                String[] time = studentInfo.get("studentBirthday").split("-");
                //?????
                student.setStudentBirthday(new java.sql.Date(Integer.parseInt(time[0])-1900,Integer.parseInt(time[1])-1,Integer.parseInt(time[2])));
                student.setStudentSex(studentInfo.get("studentSex"));
                student.setStudentImageUrl(studentImageUrl);
                if(studentService.updateStudentInfo(student)) {
                    return "redirect:/student/getinfo.do";
                }
                else {
                    return "error";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "error";
            }
        }
        else {
            return "error";
        }
    }

	@RequestMapping("/delete.do")
	public String doDelete(String studentId){
		if(studentService.deleteStudentInfo(studentId)) {
            return "redirect:/student/getinfo.do";
        }
        else
            return "error";
	}
	
	@RequestMapping("/getinfo.do")
	public ModelAndView doGetInfo(Integer contentPage){
		Integer size = 10;
		return studentService.getStudentsInfo(contentPage,size);
	}

	@RequestMapping("/getcourseinfo.do")
    public ModelAndView doGetCourseInfo(Integer contentPage,String studentId){
	    if(studentId!=null) {
            Integer size = 10;
            return studentService.getCourseInfo(contentPage, size, studentId);
        }
        else{
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("error");
	        return modelAndView;
        }
    }

    @RequestMapping("/selectcourse.do")
    public String doSelectCourse(String studentId,String courseName){
        if(studentId!=null){
            if(studentService.selectCourse(studentId,courseName)) {
                return "redirect:/student/getcourseinfo.do?studentId=" + studentId;
            }
            else {
                return "error";
            }
        }
        else {
            return "error";
        }

    }

    @RequestMapping("/abandoncourse.do")
    public String doAbandonCourse(String studentId,String courseName){
        if(studentService.abandonCourse(studentId,courseName)) {
            return "redirect:/student/getcourseinfo.do?studentId=" + studentId;
        }
        else {
            return "error";
        }
    }

    @RequestMapping("/getscoreinfo.do")
    public ModelAndView doGetScoreInfo(String studentId){
        if(studentId!=null) {
            return studentService.getStudentScoreInfo(studentId);
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    @RequestMapping("/changescore.do")
    public String doChangeScore(String studentId,String courseName,Float courseScore){
        if(studentService.updateStudentScoreInfo(studentId,courseName,courseScore)) {
            return "redirect:/student/getscoreinfo.do?studentId=" + studentId;
        }
        else {
            return "error";
        }
    }

    private String putImage(ServletFileUpload upload,HttpServletRequest request, Map<String,String> studentInfo,String studentImageUrl) throws IOException, FileUploadException {
        FileItemIterator iter = upload.getItemIterator(request);
        while (iter.hasNext()) {
            FileItemStream item = iter.next();
            String name = item.getFieldName();
            InputStream stream = item.openStream();
            if(item.isFormField()){
                studentInfo.put(name,Streams.asString(stream));
            }
            else if(!item.isFormField() && stream.available()!=0) {
                byte[] data = new byte[1024];
                int len = 0;
                FileOutputStream fileOutputStream = null;
                //绝对地址前缀
                String prefixUrl = "d://JAVA/std/src/main/webapp";
                //相对路径
                studentImageUrl = "/images/" + (int)Math.floor(Math.random() * 100 + 0.5)
                        + "/student_" + new Date().getTime() + ".jpg";
                String fullUrl = prefixUrl+studentImageUrl;
                fileOutputStream = new FileOutputStream(fullUrl);
                while ((len = stream.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
                stream.close();
                fileOutputStream.close();
            }
        }
        return studentImageUrl;
    }
}
