/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.presentation;

import com.pete.weborganizer.persistence.entities.Appointment;
import com.pete.weborganizer.persistence.entities.Person;
import com.pete.weborganizer.persistence.session.AppointmentFacade;
import com.pete.weborganizer.persistence.session.PersonFacade;
import com.pete.weborganizer.util.AccountUtils;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Named(value = "diaryController")
@SessionScoped
public class DiaryController implements Serializable 
{
    
    @EJB
    private PersonFacade personEjb;
    @EJB
    private AppointmentFacade appointmentEjb;
    
    private Appointment currentAppointment;
    
    private List<Appointment> appointments;

    public Appointment getCurrentAppointment()
    {
        return currentAppointment;
    }

    public void setCurrentAppointment(Appointment currentAppointment)
    {
        this.currentAppointment = currentAppointment;
    }
    
    
    private ArrayList<Person> participants;
    private Date startTime, endTime;
    private String unformattedParticipants;

    public String getUnformattedParticipants()
    {
        return unformattedParticipants;
    }

    public void setUnformattedParticipants(String unformattedParticipants)
    {
        this.unformattedParticipants = unformattedParticipants;
    }

    
    public Boolean getDiaryRendered() {
        return AccountUtils.checkSession();
    }
    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public ArrayList<Person> getParticipants()
    {
        return participants;
    }

    public void setParticipants(ArrayList<Person> participants)
    {
        this.participants = participants;
    }
    

    /**
     * Creates a new instance of DiaryController
     */
    public DiaryController()
    {
    }
    
    private Timestamp convertToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
    public String createAppointment() {
        
        
        this.currentAppointment.setAppointmentStart(
                convertToTimestamp(this.startTime)
        );
        this.currentAppointment.setAppointmentEnd(
                convertToTimestamp(this.endTime)
        );
        
        
        appointmentEjb.create(this.currentAppointment);
        
        return "/diary/Diary";
    }
    
    public String prepareAddAppointment() {
        this.currentAppointment = new Appointment();
        
        return "/diary/Add";
    }
    
    public String prepareDiary() {
        
        return "/diary/Diary";
    }
    
}
