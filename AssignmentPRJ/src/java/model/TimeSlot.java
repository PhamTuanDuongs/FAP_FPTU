/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author duong
 */
public class TimeSlot {
    private int SlotId;
    private Time timeFrom;
    private Time timeTo;

    public TimeSlot() {
    }

    public TimeSlot(int SlotId, Time timeFrom, Time timeTo) {
        this.SlotId = SlotId;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public int getSlotId() {
        return SlotId;
    }

    public void setSlotId(int SlotId) {
        this.SlotId = SlotId;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }
    
    
    
}
