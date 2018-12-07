/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pete.weborganizer.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.pete.weborganizer.util.HouseIdentifier;

/**
 *
 * @author UP732011 UP732011@myport.ac.uk
 */
@Entity
public class Address implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer houseNumber;
    private String houseName, houseStreet, postCodeTown, postCode;
    
    public HouseIdentifier getHouseNameOrNumber() {
        return new HouseIdentifier(this.houseName, this.houseNumber);
    }

    public String getHouseStreet()
    {
        return houseStreet;
    }

    public void setHouseStreet(String houseStreet)
    {
        this.houseStreet = houseStreet;
    }

    public String getPostCodeTown()
    {
        return postCodeTown;
    }

    public void setPostCodeTown(String postCodeTown)
    {
        this.postCodeTown = postCodeTown;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
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
        if (!(object instanceof Address))
        {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pete.weborganizer.persistence.Address[ id=" + id + " ]";
    }
    
}
