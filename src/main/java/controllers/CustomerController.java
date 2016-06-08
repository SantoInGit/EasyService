package controllers;

import ejb.CustomerEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Customer;

/**
 *
 * CustomerController:
 * A controller to handle all the operations that a customer can perform on the system after the customer has logged in
 * in the the system. It is a request scoped managed bean which connects the related views with the model(ejbs and entities)
 */
@Named(value = "customerController")
@RequestScoped
public class CustomerController {

    /**
     * Default constructor
     */
    public CustomerController() {
    }
    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<>();

    private String search = "";
    private String searchBy = "";
    private String from = "Backend";

    /**
     * Function to create a new customer. 
     * It creates a new customer and persist into the database only if the customer has not already existed.
     * @return
     */
    public String doCreateCustomer() {
        /**
         * check existing customer before registration
         */
        boolean isExist = customerEJB.checkExistingCustomerByEmail(customer.getEmail());
        if (isExist) {
            FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email address: " + customer.getEmail() + " already exist", "");
            FacesContext.getCurrentInstance().addMessage(null, infoMsg);
            return "register.xhtml";

        } else {
            customer = customerEJB.addCustomer(customer);
            FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Created Succefully.", "");
            FacesContext.getCurrentInstance().addMessage(null, infoMsg);
            if ("Frontend".equals(from)) {
                return "registerSuccess.xhtml?faces-redirect=true";
            } else {
                return "listCustomers.xhtml?faces-redirect=true";
            }
        }
    }

    /**
     *
     * @return string from
     */
    public String getFrom() {
        return from;
    }

    /**
     *
     * @param from to set the attribute from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Function to delete a customer from the database
     * @param id to be passed to find customer
     * @return string: page to be rendered
     */
    public String doDeleteCustomer(Long id) {
        customerEJB.deleteCustomer(id);
        return "listCustomers.xhtml?faces-redirect=true";
    }

    /**
     * function to search a customer
     * @return string: page to be rendered after succesful search
     */
    public String doSearch() {
        customerList = customerEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listCustomers.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doLogInCustomer() {

        return "customerProfile.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return String searchBy
     */
    public String getSearchBy() {
        return searchBy;
    }

    /**
     *
     * @param searchBy to set the attribute searchBy
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    /**
     *
     * @return String search
     */
    public String getSearch() {
        return search;
    }

    /**
     *
     * @param search to set the attribute search
     */
    public void setSearch(String search) {
        this.search = search;
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
     * @param customer to set the attribute customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * function to find a customer by customer id
     * @param customer_id to be passed to find a customer
     * @return a customer object
     */
    public Customer getCustomerById(Long customer_id) {
        customer = customerEJB.getCustomer(customer_id);
        return customer;
    }

    /**
     * function to find a customer by id passed in parameter
     * @return a customer object
     */
    public Customer getCustomerByParamId() {
        customer = customerEJB.getCustomerByParamId();
        return customer;
    }

    /**
     * function to return a list of customer object
     * @return list of customer object
     */
    public List<Customer> getCustomerList() {
        if (this.search.isEmpty()) {
            customerList = customerEJB.listCustomers();
        }
        return customerList;
    }

    /**
     * 
     * @param customerList to set the attribute customerList
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

}
