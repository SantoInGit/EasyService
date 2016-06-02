/*
AdminLogInOutController:
A session scoped controller to handle the log in and log out functionality of the admin. The variables in this
class are put in the session scope which can live for a single session
 */
package controllers;

import ejb.AdminEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;

@Named(value = "adminLogInOutController")
@SessionScoped
public class AdminLogInOutController implements Serializable {

    //controller
    public AdminLogInOutController() {
    }

    //EJB injection
    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private boolean loggedIn = false;
    private String email, password;

    //setter and getter function
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    //set and get admin object
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    //set and get email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //set and get password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //function to handle the login fucntionality of an admin.
    public String doLogInAdmin() {
        //set login related messages
        FacesMessage logInSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Succesful! ", "");
        FacesMessage logInFailure = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failure! ", "");

        //getting context of the pages and set the session map
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = extContext.getSessionMap();

        //set the admin object to the session
        sessionMap.put("admin", admin);

        //get all the admin object from the database 
        List<Admin> user = adminEJB.listAdmins();

        //default login email and password 
        if (email.equals("admin") && (password).equals("admin")) {
            loggedIn = true;
            return "dashboard.xhtml?faces-redirect=true";
        } //
        else {
            for (Admin adminFromDB : user) {
                //for the current adminobject from the database, verify the email and password. If the admin is
                //valid, redirect to the dashboard.xhtml page else keep him in the login page
                if (adminFromDB.getEmail().equals(email) && adminFromDB.getPassword().equals(password)) {
                    FacesContext.getCurrentInstance().addMessage(null, logInSuccess);
                    setAdmin(adminFromDB);
                    sessionMap.put("admin", admin);
                    loggedIn = true;
                    return "dashboard.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, logInFailure);
                    return "loginAdmin.xhtml?faces-redirect=true";
                }
            }
        }

        return null;

    }

    //function for admin logout
    public String doLogOutAdmin() {
        loggedIn = false;
        FacesMessage logOutSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log out success! ", "");
        FacesContext.getCurrentInstance().addMessage(null, logOutSuccess);
        return "loginAdmin.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    //function to redirect admin to the dashboard if admin is logged in and types other url address of pages
    public void forwardToDashboardIfLogIn() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (loggedIn) {
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/dashboard.xhtml?faces-redirect=true");
        }
    }

    //function to redirect admin to login page if admin is not logged in and types other url address of pages
    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (!loggedIn) {
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/loginAdmin.xhtml?faces-redirect=true");
        }
    }
}
