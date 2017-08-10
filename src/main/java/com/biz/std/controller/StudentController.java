package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
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
                        studentImageUrl = "/images/" + (int)Math.floor(Math.random() * 100 + 0.5)
                                + "/student_" + new Date().getTime() + ".jpg";
                        fileOutputStream = new FileOutputStream(studentImageUrl);
                        while ((len = stream.read(data)) != -1) {
                            fileOutputStream.write(data, 0, len);
                        }
                    }
                }
                Student student = new Student();
                Grade grade = new Grade();
                grade.setGradeName(studentInfo.get("studentGrade"));
                student.setStudentId(studentInfo.get("studentId"));
                student.setStudentName(studentInfo.get("studentName"));
                student.setStudentGrade(grade);
                String[] time = studentInfo.get("studentBirthday").split("-");
                student.setStudentBirthday(new java.sql.Date(Integer.parseInt(time[0])-1900,Integer.parseInt(time[1])-1,Integer.parseInt(time[2])));
                student.setStudentSex(studentInfo.get("studentSex"));
                student.setStudentImageUrl(studentImageUrl);
                studentService.updateStudentInfo(student);
                return "redirect:/student/getinfo.do";
            }catch (Exception e){
                e.printStackTrace();
                return "";
            }
        }
        else {
            return "";
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
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    String name = item.getFieldName();
                    InputStream stream = item.openStream();
                    if(item.isFormField()){
                        studentInfo.put(name,Streams.asString(stream));
                    }
                    //// TODO: 2017/8/8   寻找合适的方法将图片处理放入service完成
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
                    }
                }
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
                    return "";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "";
            }
        }
        else {
            return "";
        }
    }

	@RequestMapping("/delete.do")
	public String doDelete(String studentId){
		studentService.deleteStudentInfo(studentId);
		return "redirect:/student/getinfo.do";
	}
	
	@RequestMapping("/getinfo.do")
	public ModelAndView doGetInfo(Integer contentPage){
		Integer size = 10;
		return studentService.getStudentsInfo(contentPage,size);
	}

	@RequestMapping("/getcourseinfo.do")
    public ModelAndView doGetCourseInfo(Integer contentPage,String studentId){
        Integer size = 10;
        return studentService.getCourseInfo(contentPage,size,studentId);
    }

    @RequestMapping("/selectcourse.do")
    public ModelAndView doSelectCourse(String studentId,String courseName){

        return studentService.selectCourse(studentId,courseName);
    }

    @RequestMapping("/abandoncourse.do")
    public ModelAndView doAbandonCourse(){
        return null;
    }
}
