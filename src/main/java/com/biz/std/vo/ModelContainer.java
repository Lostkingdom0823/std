package com.biz.std.vo;

import com.biz.std.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

public class ModelContainer {

    private List<Student> students;
    private Integer maxPage;
    private Integer contentPage;

    public Integer getContentPage() {
        return contentPage;
    }

    public void setContentPage(Integer contentPage) {
        this.contentPage = contentPage;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
}
