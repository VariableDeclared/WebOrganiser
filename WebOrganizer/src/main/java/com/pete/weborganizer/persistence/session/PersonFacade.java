/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.session;

import com.pete.weborganizer.persistence.entities.Person;
import java.text.MessageFormat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person>
{

    @PersistenceContext(unitName = "com.pete_WebOrganizer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
    
    public Person checkForPersonByemail(String email) {
        return checkForPersonByParam("email", email);
    }
    public Person checkForPersonByUserName(String userName) {
        return checkForPersonByParam("userName", userName);
    }
    
    public Person checkForPersonByParam(String param, String value) {
        String formattedString = MessageFormat.format(
                "SELECT p FROM Person as p WHERE p.{0} LIKE :{0}", param
        );
        TypedQuery<Person> query = em.createQuery(
                formattedString,
                Person.class
        );
        
        try {
        return query.setParameter(param, value).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public PersonFacade()
    {
        super(Person.class);
    }
    
}
