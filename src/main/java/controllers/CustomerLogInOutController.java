package controllers;

import ejb.CustomerEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Customer;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;

@Named(value = "customerLogInOutController")
@SessionScoped
public class CustomerLogInOutController implements Serializable {

    public CustomerLogInOutController() {
    }

    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private boolean loggedIn = false;

    private String s_id = "";

    private String email, password;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public void doLogInCustomer() {
        FacesMessage logInSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Succesful: ", "");
        FacesMessage logInFailure = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failure! ", "");

        FacesContext fc = FacesContext.getCurrentInstance();
        Customer user = customerEJB.getCustomerByEmailAndPassword(email, password);

        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null, logInSuccess);
            setCustomer(user);
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = extContext.getSessionMap();
            sessionMap.put("customer", customer);
            loggedIn = true;

            if (!"".equals(s_id)) {
                fc.getApplication().getNavigationHandler().handleNavigation(
                        fc,
                        null,
                        "/frontendBookService.xhtml?faces-redirect=true&service_id=" + s_id);
            } else {
                fc.getApplication().getNavigationHandler().handleNavigation(
                        fc,
                        null,
                        "/frontendCustomerProfile.xhtml?faces-redirect=true");
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, logInFailure);
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/login.xhtml");
        }

    }

    public String doLogOutCustomer() {
        loggedIn = false;
        FacesMessage logOutSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log out success! ", "");
        FacesContext.getCurrentInstance().addMessage(null, logOutSuccess);
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!loggedIn) {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String service_id = params.get("service_id");
            if (service_id != null) {
                s_id = service_id;
                fc.getApplication().getNavigationHandler().handleNavigation(
                        fc,
                        null,
                        "/login.xhtml?faces-redirect=true");
            } else {
                fc.getApplication().getNavigationHandler().handleNavigation(
                        fc,
                        null,
                        "/login.xhtml?faces-redirect=true");
            }
        }
    }

    public void forwardToProfileIfLogin(ComponentSystemEvent cse) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (loggedIn) {
            //loggedIn = false;
            //s_id = null;
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/frontendCustomerProfile.xhtml?faces-redirect=true");
        }
    }
}
