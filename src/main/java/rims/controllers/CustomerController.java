/**
 *
 * @author jagatrp<Jagat Ram Prajapati>
 * @email prajapatijagat2009@gmail.com
 */
package rims.controllers;

import rims.ejb.CustomerEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import rims.entities.Address;
import rims.entities.Customer;

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
    private String city;
    private String zipcode;
    private String country;
    private String street;
    private String search = "";
    private String searchBy = "";

    /**
     *
     * @return String
     */
    public String doCreateCustomer() {
        Address address = new Address(city, zipcode, country, street);
        customer = customerEJB.addCustomer(customer, address);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listCustomers.xhtml";
    }

    /**
     *
     * @return
     */
    public String doSearch() {
        customerList = customerEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listCustomers.xhtml";
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

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @param customer_id
     * @return
     */
    public Customer getCustomerById(Long customer_id) {
        customer = customerEJB.getCustomer(customer_id);
        return customer;
    }

    /**
     *
     * @return
     */
    public Customer getCustomerByParamId() {
        customer = customerEJB.getCustomerByParamId();
        return customer;
    }

    /**
     *
     * @return
     */
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
