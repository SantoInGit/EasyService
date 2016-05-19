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
import javax.faces.component.UIViewRoot;

@Named(value = "adminLogInOutController")
@SessionScoped
public class AdminLogInOutController implements Serializable {

    public AdminLogInOutController() {
    }

    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private boolean loggedIn = false;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

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

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = extContext.getSessionMap();
        sessionMap.put("admin", admin);

        List<Admin> user = adminEJB.listAdmins();
        for (Admin adminFromDB : user) {
            // if (adminFromDB.getEmail().equals(this.admin.getEmail()) && (adminFromDB.getPassword()).equals(this.admin.getPassword())) {
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
        return null;

    }

    public String doLogOutAdmin() {
        loggedIn = false;
        FacesMessage logOutSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log out success! ", "");
        FacesContext.getCurrentInstance().addMessage(null, logOutSuccess);
        return "loginAdmin.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ex = fc.getExternalContext();
        String viewId = fc.getViewRoot().getViewId();
        if (!ex.getSessionMap().containsKey("admin") && !viewId.startsWith("/login")) {
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/loginAdmin.xhtml?faces-redirect=true");
        }
    }
}
