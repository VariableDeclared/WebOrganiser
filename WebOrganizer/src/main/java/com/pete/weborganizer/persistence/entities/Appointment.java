/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Entity
public class Appointment implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp appointmentStart, appointmentEnd;
    @ManyToMany
    private ArrayList<Person> participants;

    public ArrayList<Person> getParticipants()
    {
        return participants;
    }

    public void setParticipants(ArrayList<Person> participants)
    {
        this.participants = participants;
    }
    @NotNull
    private String appointmentDesc;
    
    
    public Timestamp getAppointmentStart()
    {
        return appointmentStart;
    }

    public void setAppointmentStart(Timestamp appointmentStart)
    {
        this.appointmentStart = appointmentStart;
    }

    public Timestamp getAppointmentEnd()
    {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Timestamp appointmentEnd)
    {
        this.appointmentEnd = appointmentEnd;
    }

    public String getAppointmentDesc()
    {
        return appointmentDesc;
    }

    public void setAppointmentDesc(String appointmentDesc)
    {
        this.appointmentDesc = appointmentDesc;
    }

    


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment))
        {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pete.weborganizer.persistence.Appointment[ id=" + id + " ]";
    }
    
}
