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

/**
 *
 * AdminLogInOutController:
 * A session scoped controller to handle the log in and log out functionality of the admin. The variables in this
 * class are put in the session scope which can live for a single session
 */
@Named(value = "adminLogInOutController")
@SessionScoped
public class AdminLogInOutController implements Serializable {

    //controller

    /**
     *Default constructor
     */
    public AdminLogInOutController() {
    }

    //EJB injection
    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private boolean loggedIn = false;
    private String email, password;

    //setter and getter function

    /**
     *
     * @param loggedIn to set the attribute loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    //set and get admin object

    /**
     *
     * @return the admin object
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     *
     * @param admin to set the attribute admin
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    //set and get email

    /**
     *
     * @return  email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email to set the attribute email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    //set and get password

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password to set the attribute password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //function to handle the login fucntionality of an admin.

    /**
     * function to handle the login fucntionality of an admin.
     * @return string: name of the page as per the success / failure of login
     */
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

    /**
     * function for admin logout
     * @return string: name of the page after admin logs out
     */
    public String doLogOutAdmin() {
        loggedIn = false;
        FacesMessage logOutSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log out success! ", "");
        FacesContext.getCurrentInstance().addMessage(null, logOutSuccess);
        return "loginAdmin.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return boolean loggedIn attribute
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    //function to redirect admin to the dashboard if admin is logged in and types other url address of pages

    /**
     * function to redirect admin to the dashboard if admin is logged in and types other url address of pages
     */
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

    /**
     * function to redirect admin to login page if admin is not logged in and types other url address of pages
     * @param cse
     */
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
