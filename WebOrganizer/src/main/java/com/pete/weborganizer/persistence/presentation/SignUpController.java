/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.presentation;

import com.pete.weborganizer.persistence.entities.Address;
import com.pete.weborganizer.persistence.entities.Person;
import com.pete.weborganizer.persistence.session.AddressFacade;
import com.pete.weborganizer.persistence.session.PersonFacade;
import com.pete.weborganizer.util.AccountUtils;
import com.pete.weborganizer.util.HouseIdentifier;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Named(value = "homeController")
@SessionScoped
public class SignUpController implements Serializable
{

    @EJB
    private PersonFacade personEjb;
    @EJB
    private AddressFacade addressEjb;
    
    private Person currentUser;
    private Boolean isHouseName;
    private Integer houseNumber;
    private String password;

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Boolean getIsHouseName()
    {
        return isHouseName;
    }

    public void setIsHouseName(Boolean isHouseName)
    {
        this.isHouseName = isHouseName;
    }

    public Integer getHouseNumber()
    {
        return houseNumber;
    }

    public String createUser() {
        this.currentUser.setPwHash(AccountUtils.hashPassword(this.password));
        
        if(personEjb.checkForPersonByUserName(this.currentUser.getUserName()) != null)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("User name exists.")
            );
        }
        
        if(personEjb.checkForPersonByUserName(this.currentUser.getEmail()) != null)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Email exists")
            );
        }
        
        Address addr = this.currentUser.getAddress();
        HouseIdentifier houseNameOrNumber;
        if(this.isHouseName) {
            houseNameOrNumber = new HouseIdentifier(
                    this.houseName,
                    0
            );
        } else {
            houseNameOrNumber = new HouseIdentifier(
                    "Sollas",
                    this.houseNumber
            );
        }
        addr.setHouseNameOrNumber(houseNameOrNumber);
        
        
        addressEjb.create(this.currentUser.getAddress());
        personEjb.create(this.currentUser);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("currentUser", this.currentUser);
        session.setAttribute("authenticated", true);
        return "/login/Success";
    }
    public void setHouseNumber(Integer houseNumber)
    {
        this.houseNumber = houseNumber;
    }
    
    

    public String getHouseName()
    {
        return houseName;
    }

    
    public void setHouseName(String houseName)
    {
        this.houseName = houseName;
    }
    private String houseName;
    
    
    
   
    public Person getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser)
    {
        this.currentUser = currentUser;
    }
    
    public String prepareAddress() {
        this.currentUser.setAddress(new Address());
        return "/home/AddAddress";
    }
    
    public String prepareCreateAccount() {
        this.currentUser = new Person();
        return "/home/CreateAccountForm";
    }
    /**
     * Creates a new instance of HomeController
     */
    public SignUpController()
    {
    }
    
    public String createAccount() { 
        return "/home/CreateAccount";
    }
    
}
