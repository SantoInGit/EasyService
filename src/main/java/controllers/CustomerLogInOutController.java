package controllers;

import ejb.CustomerEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Customer;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;

/**
 * CustomerLogInOutController:
 * A session scoped controller to handle the log in and log out functionality of the customer. The variables in this
 * class are put in the session scope which can live for a single session
 * 
 */
@Named(value = "customerLogInOutController")
@SessionScoped
public class CustomerLogInOutController implements Serializable {

    /**
     * Default constructor
     */
    public CustomerLogInOutController() {
    }

    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private boolean loggedIn = false;

    private String s_id = "";

    private String email, password;

    /**
     *
     * @param loggedIn to set the attribute loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     * @return String s_id
     */
    public String getS_id() {
        return s_id;
    }

    /**
     *
     * @param s_id to set the attribute s_id
     */
    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    /**
     *
     * @return a customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer to the attribute customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email to the attribute email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return string password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password to the attribute password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //function to handle the login fucntionality of an customer.

    /**
     * function to handle the login functionality of an customer.
     * @return string: name of the page as per the success / failure of login
     */
    public void doLogInCustomer() {
        FacesMessage logInSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Succesful: ", "");
        FacesMessage logInFailure = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Failure! ", "");

        FacesContext fc = FacesContext.getCurrentInstance();
        List<Customer> user = customerEJB.getCustomerByEmailAndPassword(email, password);

        for (Customer customerFromDB : user) {
            if (customerFromDB.getEmail().equals(email) && customerFromDB.getPassword().equals(password)) {
                FacesContext.getCurrentInstance().addMessage(null, logInSuccess);
                setCustomer(customerFromDB);
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

    }

     //function for customer logout

    /**
     * function for customer logout
     * @return string: name of the page after customer logs out
     */
    public String doLogOutCustomer() {
        loggedIn = false;
        s_id = "";
        FacesMessage logOutSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log out success! ", "");
        FacesContext.getCurrentInstance().addMessage(null, logOutSuccess);
        return "login.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return boolean loggedIn attribute
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    //function to redirect customer to login page if customer is not logged in and types other url address of pages

    /**
     * function to redirect customer to login page if customer is not logged in and types other url address of pages
     * @param cse
     */
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

    /**
     * function to redirect customer to customer profile page if customer is already logged in
     * @param cse
     */
    public void forwardToProfileIfLogin(ComponentSystemEvent cse) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (loggedIn) {
            fc.getApplication().getNavigationHandler().handleNavigation(
                    fc,
                    null,
                    "/frontendCustomerProfile.xhtml?faces-redirect=true");
        }
    }
}
