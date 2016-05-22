
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

    public AdminController() {
    }
    
    @EJB
    private AdminEJB adminEJB;
    private Admin admin = new Admin();
    private List<Admin> adminList = new ArrayList<>();
   
    private String search = "";
    private String searchBy = "";

 
    public String doCreateAdmin() {
        admin = adminEJB.addAdmin(admin);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml?faces-redirect=true";
    }
 
    public String doSearch() {
        adminList = adminEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listAdmins.xhtml?faces-redirect=true";
    }
    public String doDeleteAdmin(Long id){
        adminEJB.deleteAdmin(id);
        return "listAdmins.xhtml?faces-redirect=true";
    }
    
    public String doLogInAdmin(){
        List<Admin> user = adminEJB.listAdmins();
        for(Admin adminFromDB:user){
            if(adminFromDB.getEmail().equals(this.admin.getEmail()) && (adminFromDB.getPassword()).equals(this.admin.getPassword())){
                return "listAdmins.xhtml?faces-redirect=true";
            }
        }
        return "loginAdmin.xhtml?faces-redirect=true";
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
