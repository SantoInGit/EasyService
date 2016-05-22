
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

@Named(value = "customerController")
@RequestScoped
public class CustomerController {

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
    }
    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<>();

    private String search = "";
    private String searchBy = "";

 
    public String doCreateCustomer() {
        customer = customerEJB.addCustomer(customer);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listCustomers.xhtml?faces-redirect=true";
    }
    public String doDeleteCustomer(Long id){
        customerEJB.deleteCustomer(id);
        return "listCustomers.xhtml?faces-redirect=true";
    }

  
    public String doSearch() {
        customerList = customerEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listCustomers.xhtml?faces-redirect=true";
    }
    
    public String doLogInCustomer(){
        
        return "customerProfile.xhtml?faces-redirect=true";
    }
    
   

    /**
     *
     * @return String
     */
    public String getSearchBy() {
        return searchBy;
    }

    /**
     *
     * @param searchBy
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    /**
     *
     * @return String
     */
    public String getSearch() {
        return search;
    }

    /**
     *
     * @param search
     */
    public void setSearch(String search) {
        this.search = search;
    }

    public Customer getCustomer() {
        return customer;
    }

 
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

 
    public Customer getCustomerById(Long customer_id) {
        customer = customerEJB.getCustomer(customer_id);
        return customer;
    }

 
    public Customer getCustomerByParamId() {
        customer = customerEJB.getCustomerByParamId();
        return customer;
    }

 
    public List<Customer> getCustomerList() {
        if (this.search.isEmpty()) {
            customerList = customerEJB.listCustomers();
        }
        return customerList;
    }

    /**
     *
     * @param customerList
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

  

}
