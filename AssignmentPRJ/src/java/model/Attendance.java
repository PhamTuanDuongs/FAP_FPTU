/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author duong
 */
public class Attendance {

    private Student student;
    private Lecture Lecture;
    private String status;
    private String comment;
    private Timestamp recordTime;

    public Attendance() {
    }

    public Attendance(Student student, Lecture Lecture, String status, String comment, Timestamp recordTime) {
        this.student = student;
        this.Lecture = Lecture;
        this.status = status;
        this.comment = comment;
        this.recordTime = recordTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return Lecture;
    }

    public void setLecture(Lecture Lecture) {
        this.Lecture = Lecture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }
    
    
    
    
}
