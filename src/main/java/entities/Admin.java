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
    @NamedQuery(name = "findAdminStaff", query = "select a from Admin a"),
    @NamedQuery(name = "findAdminByFirstName", query = "select a from Admin a where UPPER(a.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findAdminByLastName", query = "select a from Admin a where UPPER(a.lastName) LIKE :LastName"),
    @NamedQuery(name = "findAdminByAddress", query = "select a from Admin a where UPPER(a.address.street) LIKE :Address"),
    @NamedQuery(name = "findAdminById", query = "select a from Admin a where a.id=:id")
})
public class Admin extends User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String qualification;
    private String status;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(
           name = "jnd_Admin_Invoice",
            joinColumns = @JoinColumn(name="admin_invoice_fk")
    )
    private List<Invoice> invoiceList;
   
    
    public Admin(){
        
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
