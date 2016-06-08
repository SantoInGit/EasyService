
package controllers;

import ejb.StaffEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Staff;

/**
 *
 * A request scoped controller to handle all the operations related to staff objects
 */
@Named(value = "staffController")
@RequestScoped
public class StaffController {

    /**
     * Default constructor
     */
    public StaffController() {
    }
    //EJB injection
    @EJB
    private StaffEJB staffEJB;
    private Staff staff = new Staff();
    private List<Staff> staffList = new ArrayList<>();
    private String search = "";
    private String searchBy = "";
   
    /**
     * Function to create a staff object and persist in the database
     * @return string: name of the page to be rendered
     */
    public String doCreateStaff() {
        
        staff = staffEJB.addStaff(staff);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml?faces-redirect=true";
    }

    /**
     * Function to perform search on staff object
     * @return string: name of the page to be rendered
     */
    public String doSearch() {
        staffList = staffEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml?faces-redirect=true";
    }
    
    /**
     * Function to delete staff object from database
     * @param id to be passed to find the staff object
     * @return string: name of the page to be rendered
     */
    public String doDeleteStaff(Long id){
        staffEJB.deleteStaff(id);
        return "listStaffs.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String getSearchBy() {
        return searchBy;
    }

    /**
     *
     * @param searchBy
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    /**
     *
     * @return
     */
    public String getSearch() {
        return search;
    }

    /**
     *
     * @param search
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     *
     * @return
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     *
     * @param staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     *
     * @param staff_id
     * @return
     */
    public Staff getStaffById(Long staff_id) {
        staff = staffEJB.getStaff(staff_id);
        return staff;
    }

    /**
     *
     * @return
     */
    public Staff getStaffByParamId() {
        staff = staffEJB.getStaffByParamId();
        return staff;
    }

    /**
     *
     * @return
     */
    public List<Staff> getStaffList() {
        if (this.search.isEmpty()) {
            staffList = staffEJB.listStaffs();
        }
        return staffList;
    }

    /**
     *
     * @param staffList
     */
    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
    
    /**
     * Function to return list of staff who are unassigned for any task
     * @param fromDate to be passed to set fromDate
     * @param toDate to be passed to set toDate
     * @return list of unassigned staff in give date range.
     */
    public List<Staff> getUnAssignedStaff(String fromDate, String toDate){
       
        return staffEJB.getUnAssignedStaff(fromDate, toDate);
         
    }
  

}
