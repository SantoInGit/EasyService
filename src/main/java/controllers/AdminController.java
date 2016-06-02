/*
AdminController:
A controller to handle all the operations that admin can perform on the system after the admin has logged in
in the the system. It is a request scoped managed bean which connects the related views with the model(ejbs and entities)

*/
package controllers;

import ejb.AdminEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "adminController")
@RequestScoped
public class AdminController implements Serializable{
    
    //constructor
    public AdminController() {
    }
    
    //Injecting adminEJB to the controller
    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private List<Admin> adminList = new ArrayList<>();
   
    private String search = "";
    private String searchBy = "";

    //function to delegate the persistence of admin object to the AdminEJB. Returns the names of the page to be rendered next.
    public String doCreateAdmin() {
        
        admin = adminEJB.addAdmin(admin);
        //setting message in case of successful persistence of admin object in the database
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml?faces-redirect=true";
    }
    
    //function to handle the search functionality of the admin. Delegates the function to the AdminEJB. Returns the 
    //name of the view page to be rendered next.
    public String doSearch() {
        
        adminList = adminEJB.search(search, searchBy);        
        //setting message in case of successful search of admin object in the database
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml?faces-redirect=true";
    }
    
    //functio to remove a particular admin object from the database. Retrns the name of the view page to be
    //rendered next
    public String doDeleteAdmin(Long id){
        adminEJB.deleteAdmin(id);
        return "listAdmins.xhtml?faces-redirect=true";
    }
    
    //function to handle the login procedure of an admin object. It temporarily saves all the admins from the
    //database and matches the email and password with all the admin. If the match is succesful, admin login is 
    //successful , otherwise returns to the same page.
    public String doLogInAdmin(){
        List<Admin> user = adminEJB.listAdmins();
        for(Admin adminFromDB:user){
            if(adminFromDB.getEmail().equals(this.admin.getEmail()) && (adminFromDB.getPassword()).equals(this.admin.getPassword())){
                return "listAdmins.xhtml?faces-redirect=true";
            }
        }
        return "loginAdmin.xhtml?faces-redirect=true";
    }
    
    //getter and setter fucntions
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
