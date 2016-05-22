package controllers;

import ejb.StaffEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Staff;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "staffEditController")
@RequestScoped
public class StaffEditController implements Serializable {

    public StaffEditController() {
    }

    @EJB
    private StaffEJB staffEJB;
    private static Staff staff = new Staff();

    public String doEditStaff(Long id) {
        staff = staffEJB.getStaff(id);
        return "editStaff.xhtml?faces-redirect=true";
    }

    public String doEditStaffCommit() {

        int a = staffEJB.editStaffCommit(staff);
        if (a >= 0) {
            FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Edited", "");
            FacesContext.getCurrentInstance().addMessage(null, infoMsg);
           
        }

        return "listStaffs.xhtml?faces-redirect=true";
    }
    
    public String doCancelEditStaff(){
        return "listStaffs.xhtml?faces-redirect=true";
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        StaffEditController.staff = staff;
    }
}
