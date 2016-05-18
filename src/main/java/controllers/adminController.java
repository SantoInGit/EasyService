
package controllers;

import ejb.AdminEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Address;
import entities.Admin;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "adminController")
@RequestScoped
public class adminController implements Serializable{

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
        admin = adminEJB.addAdmin(admin, address);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Created Succefully.", "");
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
        List<Admin> user = adminEJB.listAdmins();
        for(Admin adminFromDB:user){
            if(adminFromDB.getEmail().equals(this.admin.getEmail()) && (adminFromDB.getPassword()).equals(this.admin.getPassword())){
                
                return "listAdmins.xhtml";
            }
        }
        return "loginAdmin.xhtml";
    }
    
 
    public String getSearchBy() {
        return searchBy;
    }


    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }


    public String getSearch() {
        return search;
    }


    public void setSearch(String search) {
        this.search = search;
    }

 
    public String getCity() {
        return city;
    }

 
    public String getZipcode() {
        return zipcode;
    }


    public String getCountry() {
        return country;
    }


    public String getStreet() {
        return street;
    }

 
    public void setCity(String city) {
        this.city = city;
    }

 
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

 
    public void setStreet(String street) {
        this.street = street;
    }

   
    public Admin getAdmin() {
        return admin;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdminById(Long adminId) {
        admin = adminEJB.getAdmin(adminId);
        return admin;
    }


    public Admin getAdminByParamId() {
        admin = adminEJB.getAdminByParamId();
        return admin;
    }


    public List<Admin> getAdminList() {
        if (this.search.isEmpty()) {
            adminList = adminEJB.listAdmins();
        }
        return adminList;
    }


    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

  

}
