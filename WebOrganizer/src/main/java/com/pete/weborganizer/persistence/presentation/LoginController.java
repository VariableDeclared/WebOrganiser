/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence.presentation;

import com.pete.weborganizer.persistence.entities.Person;
import com.pete.weborganizer.persistence.session.PersonFacade;
import com.pete.weborganizer.util.AccountUtils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable
{

    
    private Person currentUser;
    private String password;
    private ArrayList<Person> users;

    public ArrayList<Person> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<Person> users)
    {
        this.users = users;
    }
    

    public String goHome() {
        return "/index";
    }
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    @EJB
    private PersonFacade personEjb;

    public Person getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser)
    {
        this.currentUser = currentUser;
    }
    /**
     * Creates a new instance of LoginController
     */
    public LoginController()
    {
    }
    
    public String login() {
        Person user = personEjb.checkForPersonByUserName(
                this.currentUser.getUserName()
        );
        
        if (user != null) {
            if (user.getPwHash().equals(AccountUtils.hashPassword(this.password))){
                HttpSession session = (HttpSession) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .getSession(true);
                session.setAttribute("currentUser", user);
                session.setAttribute("authenticated", true);
               return "Success";
            }
        }
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Username or password incorrect")
                );
        return "";
    }
    
    public String prepareLogin() {
        this.currentUser = new Person();
        return "/login/Login";
    }
    
    public Boolean checkSession(){
        
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        return AccountUtils.checkSession();
    }
    
    private String listUsers() {
        
        
        return "/login/ListUsers";
        
    }
    
   
    public String deleteUser(Person user){
        personEjb.remove(user);
        
        return this.preparelistUsers();
    }
    public String preparelistUsers() {
        
        if (AccountUtils.checkSession()) {
            
            this.users = new ArrayList(personEjb.findAll());
            return this.listUsers();
        }
        this.users = new ArrayList<>();
        
        FacesContext.getCurrentInstance().addMessage("Not Autenticated", null);
        return "";
    }
    
    
    
    
}
