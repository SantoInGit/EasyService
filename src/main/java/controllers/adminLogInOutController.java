package controllers;

import ejb.AdminEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@Named(value = "adminLogInOutController")
@SessionScoped
public class adminLogInOutController implements Serializable {

    public adminLogInOutController() {
    }

    @EJB
    private AdminEJB adminEJB;

    private Admin admin = new Admin();

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String doLogInAdmin() {
        FacesMessage logInSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Succesful: ", "");
        FacesMessage logInFailure = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failure! ", "");
        List<Admin> user = adminEJB.listAdmins();
        for (Admin adminFromDB : user) {
           // if (adminFromDB.getEmail().equals(this.admin.getEmail()) && (adminFromDB.getPassword()).equals(this.admin.getPassword())) {
           if(adminFromDB.getEmail().equals(email) && adminFromDB.getPassword().equals(password)){
                FacesContext.getCurrentInstance().addMessage(null, logInSuccess);
                setAdmin(adminFromDB);
                return "dashboard.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, logInFailure);
                return "loginAdmin.xhtml";
            }
        }
        return "";

    }
    
    public String isLogedIn(){
        //if(admin.getId() == null)
            return "loginAdmin.xhtml";
        //return "";
            
    }
}
