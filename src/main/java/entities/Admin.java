/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Santo
 */
@Entity
@NamedQueries({
    
    //search queries
    @NamedQuery(name = "findAllAdmin", query = "select a from Admin a"),
    @NamedQuery(name = "findAdminByFirstName", query = "select a from Admin a where UPPER(a.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findAdminByLastName", query = "select a from Admin a where UPPER(a.lastName) LIKE :LastName"),
    @NamedQuery(name = "findAdminByAddress", query = "select a from Admin a where UPPER(a.address.street) LIKE :Address"),
    @NamedQuery(name = "findAdminById", query = "select a from Admin a where a.id=:id"),
    
    //update query
    @NamedQuery(name = "updateAdmin", //query = "update Admin a Set a.email=?1, a.firstName=?2 Where a.id = ?3")
            query="update Admin a Set  a.email = :adEmail, a.firstName = :adFirstName, a.lastName = :adLastName, "
                    + "a.middleName = :adMiddleName, a.password = :adPassword, a.phoneNo = :adPhoneNo, a.qualification = :adQualification, "
                    + "a.status = :adStatus, a.userType = :adUserType, a.address.city = :adCity, a.address.country = :adCountry, "
                    + "a.address.state = :adState, a.address.street = :adStreet, a.address.zipCode = :adZipCode "
                    + "where a.id = :adId")       
})
public class Admin extends User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String qualification;
    private String status;
    
    public Admin(){
        
    }
 
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
