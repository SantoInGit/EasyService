package controllers;

import ejb.AdminEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "adminEditController")
@RequestScoped
public class AdminEditController implements Serializable {

    public AdminEditController() {
    }

    @EJB
    private AdminEJB adminEJB;
    private static Admin admin = new Admin();

    public String doEditAdmin(Long id) {
        admin = adminEJB.getAdmin(id);
        return "editAdmin.xhtml?faces-redirect=true";
    }

    public String doEditAdminCommit() {

        int a = adminEJB.editAdminCommit(admin);
        if (a >= 0) {
            FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Edited", "");
            FacesContext.getCurrentInstance().addMessage(null, infoMsg);
           
        }

        return "listAdmins.xhtml?faces-redirect=true";
    }
    
    public String doCancelEditAdmin(){
        return "listAdmins.xhtml?faces-redirect=true";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        AdminEditController.admin = admin;
    }
}
