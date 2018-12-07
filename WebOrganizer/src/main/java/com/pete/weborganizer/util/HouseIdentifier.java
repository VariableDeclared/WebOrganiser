/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.util;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
public class HouseIdentifier
{
    
    Boolean isHouseName;
    Integer houseNumber;
    String houseName;

    public HouseIdentifier()
    {
        this.isHouseName = false;
        this.houseNumber = 1;
        this.houseName = "Sollas";
    }
    
    public HouseIdentifier(String houseName, Integer houseNumber){
        if (houseName.isEmpty()) {
            this.houseName = "Sollas";
            this.houseNumber = houseNumber;
            this.isHouseName = false;
        } else {
            this.houseName = houseName;
            this.houseNumber = 1;
            this.isHouseName = true; 
        }
        
        
        
    }
    
    @Override
    public String toString() {
        if (this.isHouseName) {
            return this.houseName;
        } else {
            return this.houseNumber.toString();
        }
    }

}
