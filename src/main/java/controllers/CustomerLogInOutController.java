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
    
    private String email, password;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    public String doLogInCustomer() {
        FacesMessage logInSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Succesful: ", "");
        FacesMessage logInFailure = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failure! ", "");

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = extContext.getSessionMap();
        sessionMap.put("customer", customer);

        List<Customer> user = customerEJB.listCustomers();
        for (Customer customerFromDB : user) {
            // if (customerFromDB.getEmail().equals(this.customer.getEmail()) && (customerFromDB.getPassword()).equals(this.customer.getPassword())) {
            if (customerFromDB.getEmail().equals(email) && customerFromDB.getPassword().equals(password)) {
                FacesContext.getCurrentInstance().addMessage(null, logInSuccess);
                setCustomer(customerFromDB);
                sessionMap.put("customer", customer);
                loggedIn = true;
                return "frontendListServices.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, logInFailure);
                return "index.xhtml?faces-redirect=true";
            }
        }
        return null;

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
        ExternalContext ex = fc.getExternalContext();
        String viewId = fc.getViewRoot().getViewId();
        if (!ex.getSessionMap().containsKey("customer") && !viewId.startsWith("/login")) {
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/login.xhtml?faces-redirect=true");
        }
    }
}
