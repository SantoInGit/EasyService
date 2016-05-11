
package controllers;

import ejb.StaffEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Address;
import entities.Staff;

@Named(value = "staffController")
@RequestScoped
public class StaffController {

    /**
     * Creates a new instance of CustomerController
     */
    public StaffController() {
    }
    @EJB
    private StaffEJB staffEJB;
    private Staff staff = new Staff();
    private List<Staff> staffList = new ArrayList<>();
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
    public String doCreateStaff() {
        Address address = new Address(city, zipcode, country, street);
        staff = staffEJB.addStaff(staff, address);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml";
    }

    /**
     *
     * @return
     */
    public String doSearch() {
        staffList = staffEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listStaffs.xhtml";
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
    public Staff getStaff() {
        return staff;
    }

    /**
     *
     * @param staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     *
     * @param staff_id
     * @return
     */
    public Staff getStaffById(Long staff_id) {
        staff = staffEJB.getStaff(staff_id);
        return staff;
    }

    /**
     *
     * @return
     */
    public Staff getStaffByParamId() {
        staff = staffEJB.getStaffByParamId();
        return staff;
    }

    /**
     *
     * @return
     */
    public List<Staff> getStaffList() {
        if (this.search.isEmpty()) {
            staffList = staffEJB.listStaffs();
        }
        return staffList;
    }

    /**
     *
     * @param staffList
     */
    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

  

}
