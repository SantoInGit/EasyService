/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Santo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllStaff", query = "select s from Staff s"),
    @NamedQuery(name = "findStaffByFirstName", query = "select s from Staff s where UPPER(s.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findStaffByLastName", query = "select s from Staff s where UPPER(s.lastName) LIKE :LastName"),
    @NamedQuery(name = "findStaffByAddress", query = "select s from Staff s where UPPER(s.address.street) LIKE :Address"),
    @NamedQuery(name = "findStaffById", query = "select s from Staff s where s.id=:id"),
//update query
    @NamedQuery(name = "updateStaff", //query = "update Admin a Set a.email=?1, a.firstName=?2 Where a.id = ?3")
            query="update Staff a Set  a.email = :stEmail, a.firstName = :stFirstName, a.jobTitle = :stJobTitle,"
                    + "a.lastName = :stLastName, a.middleName=:stMiddleName, a.password = :stPassword,"
                    + " a.phoneNo = :stPhoneNo, a.qualification = :stQualification, a.status = :stStatus, "
                    + "a.userType = :stUserType, a.address.city = :stCity, a.address.country = :stCountry, "
                    + "a.address.state = :stState, a.address.street = :stStreet, a.address.zipCode = :stZipCode "
                    + "where a.id = :stId")       
})        
        

public class Staff extends User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String qualification;
    private String jobTitle;
    private String status;
   
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="jnd_Staff_ServiceOrderItem",
            joinColumns = @JoinColumn(name = "staff_serviceOrderItem_fk")
    )
    private List<ServiceOrderItem> serviceOrderItem;

    public Staff() {
    }
    

 
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return the jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
