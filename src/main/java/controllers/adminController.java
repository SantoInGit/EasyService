
package controllers;

import ejb.AdminEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Address;
import entities.Admin;

@Named(value = "customerController")
@RequestScoped
public class adminController {

    /**
     * Creates a new instance of CustomerController
     */
    public adminController() {
    }
    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private List<Admin> adminList = new ArrayList<>();
    private String city;
    private String zipcode;
    private String country;
    private String street;
    private String search = "";
    private String searchBy = "";

 
    public String doCreateAdmin() {
        Address address = new Address(city, zipcode, country, street);
        admin = adminEJB.addCustomer(admin, address);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml";
    }

  
    public String doSearch() {
        adminList = adminEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml";
    }
    
    public String doLogInAdmin(){
        
        return "adminProfile.xhtml";
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
    public Admin getAdmin() {
        return admin;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Admin admin) {
        this.admin = admin;
    }

    /**
     *
     * @param customer_id
     * @return
     */
    public Admin getAdminById(Long adminId) {
        admin = adminEJB.getCustomer(adminId);
        return admin;
    }

    /**
     *
     * @return
     */
    public Admin getCustomerByParamId() {
        admin = adminEJB.getCustomerByParamId();
        return admin;
    }

    /**
     *
     * @return
     */
    public List<Admin> getCustomerList() {
        if (this.search.isEmpty()) {
            adminList = adminEJB.listCustomers();
        }
        return adminList;
    }

    /**
     *
     * @param customerList
     */
    public void setCustomerList(List<Admin> customerList) {
        this.adminList = adminList;
    }

  

}
