/*
 * Staff entity class.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * An entity class to represent a staff object
 */
@Entity
@NamedQueries({
    //select queries for staffs
    @NamedQuery(name = "findAllStaff", query = "select s from Staff s"),
    @NamedQuery(name = "findStaffByFirstName", query = "select s from Staff s where UPPER(s.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findStaffByLastName", query = "select s from Staff s where UPPER(s.lastName) LIKE :LastName"),
    @NamedQuery(name = "findStaffByAddress", query = "select s from Staff s where UPPER(s.address.street) LIKE :Address"),
    @NamedQuery(name = "findStaffById", query = "select s from Staff s where s.id=:id"),
    
    //update query for staff
    @NamedQuery(name = "updateStaff", 
            query = "update Staff a Set  a.email = :stEmail, a.firstName = :stFirstName, "
            + "a.fromDate=:stFromDate, a.jobTitle= :stJobTitle,"
            + "a.lastName = :stLastName, a.middleName=:stMiddleName, a.password = :stPassword,"
            + " a.phoneNo = :stPhoneNo, a.qualification = :stQualification, a.status = :stStatus, "
            + "a.toDate = :stToDate, a.userType = :stUserType, a.address.city = :stCity, a.address.country = :stCountry, "
            + "a.address.state = :stState, a.address.street = :stStreet, a.address.zipCode = :stZipCode "
            + "where a.id = :stId"),
    
    //update query for assigned staff 
    @NamedQuery(name = "updateAssignedStaff",
            query = "UPDATE Staff s SET s.jobTitle = :stJobTitle, s.fromDate = :stFromDate, s.toDate = :stToDate,"
            + "s.status = :stStatus WHERE s.id = :stId"),
    
    @NamedQuery(name = "findAllUnAssignedStaffs",
            query = "SELECT s from Staff s WHERE s.status != :stStatus or s.toDate < :orderStartDate or s.fromDate > :orderToDate")
})

public class Staff extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String qualification;
    private String jobTitle;
    private String fromDate;
    private String toDate;
    private String status = "Not Assigned";

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "jnd_Staff_ServiceOrderItem",
            joinColumns = @JoinColumn(name = "staff_serviceOrderItem_fk")
    )
    private List<ServiceOrderItem> serviceOrderItem;

    //constructor

    /**
     * Default constructor
     */
    public Staff() {
    }

    //getter and setter functions for staff attributes

    /**
     *
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     *
     * @return qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     *
     * @param qualification to set the attribute qualification
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     *
     * @return jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     *
     * @param jobTitle to set the attribute jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     *
     * @return fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     *
     * @param fromDate to set the attribute fromDate
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     *
     * @return toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     *
     * @param toDate to set the attribute toDate
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status to set the attribute status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return serviceOrderItem list
     */
    public List<ServiceOrderItem> getServiceOrderItem() {
        return serviceOrderItem;
    }

    /**
     *
     * @param serviceOrderItem to set the attribute serviceOrderItem
     */
    public void setServiceOrderItem(List<ServiceOrderItem> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }
}
