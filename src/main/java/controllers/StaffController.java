
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

@Named(value = "staffController")
@RequestScoped
public class StaffController {
    public StaffController() {
    }
    @EJB
    private StaffEJB staffEJB;
    private Staff staff = new Staff();
    private List<Staff> staffList = new ArrayList<>();
    private String search = "";
    private String searchBy = "";
   

    public String doCreateStaff() {
        
        staff = staffEJB.addStaff(staff);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml?faces-redirect=true";
    }

    public String doSearch() {
        staffList = staffEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml?faces-redirect=true";
    }
    
    public String doDeleteStaff(Long id){
        staffEJB.deleteStaff(id);
        return "listStaffs.xhtml?faces-redirect=true";
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaffById(Long staff_id) {
        staff = staffEJB.getStaff(staff_id);
        return staff;
    }

    public Staff getStaffByParamId() {
        staff = staffEJB.getStaffByParamId();
        return staff;
    }

    public List<Staff> getStaffList() {
        if (this.search.isEmpty()) {
            staffList = staffEJB.listStaffs();
        }
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
    
    public List<Staff> getUnAssignedStaff(String fromDate, String toDate){
       
        return staffEJB.getUnAssignedStaff(fromDate, toDate);
         
    }
  

}
