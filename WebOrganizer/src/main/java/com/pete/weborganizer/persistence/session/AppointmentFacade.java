/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.session;

import com.pete.weborganizer.persistence.entities.Appointment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment>
{

    @PersistenceContext(unitName = "com.pete_WebOrganizer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
    
   

    public AppointmentFacade()
    {
        super(Appointment.class);
    }
    
}
