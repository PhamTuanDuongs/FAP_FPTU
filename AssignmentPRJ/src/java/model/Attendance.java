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
    private LectureForStudent lecture;
    private String status;
    private String comment;
    private TimeSlot slot;
    private Timestamp recordTime;
    private Room room;
    private Course course;

    public Attendance() {
    }

    public Attendance(Student student, LectureForStudent lecture, String status, String comment, TimeSlot slot, Timestamp recordTime, Room room, Course course) {
        this.student = student;
        this.lecture = lecture;
        this.status = status;
        this.comment = comment;
        this.slot = slot;
        this.recordTime = recordTime;
        this.room = room;
        this.course = course;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LectureForStudent getLecture() {
        return lecture;
    }

    public void setLecture(LectureForStudent Lecture) {
        this.lecture = Lecture;
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
