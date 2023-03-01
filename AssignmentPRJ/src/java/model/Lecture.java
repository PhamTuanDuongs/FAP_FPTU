/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author duong
 */
public class Lecture {
    private int lessonId;
    private Course course;
    private Room room;
    private Group group;
    private Instructor instructor;
    private String status;
    private TimeSlot slotId;
    private Date date;

    public Lecture() {
    }

    public Lecture(int lessonId, Course course, Room room, Group group, Instructor instructor, String status, TimeSlot slotId, Date date) {
        this.lessonId = lessonId;
        this.course = course;
        this.room = room;
        this.group = group;
        this.instructor = instructor;
        this.status = status;
        this.slotId = slotId;
        this.date = date;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TimeSlot getSlotId() {
        return slotId;
    }

    public void setSlotId(TimeSlot slotId) {
        this.slotId = slotId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


   
}
