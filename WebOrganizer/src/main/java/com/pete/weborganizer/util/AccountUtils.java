/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
public class AccountUtils
{
    
    public static Boolean checkSession () {
        HttpSession session =  (HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(true);
        return (
                session.getAttribute("authenticated") != null &&
                session.getAttribute("currentUser") != null
            );
    }
    public static String hashPassword(String pw) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pw.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        
        return generatedPassword;
    }
}
