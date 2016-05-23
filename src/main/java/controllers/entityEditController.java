package controllers;

import ejb.AdminEJB;
import ejb.CustomerEJB;
import ejb.StaffEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import entities.Customer;
import entities.Staff;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "entityEditController")
@RequestScoped
public class entityEditController implements Serializable {

    public entityEditController() {
    }

    @EJB
    private AdminEJB adminEJB;
    @EJB
    private CustomerEJB customerEJB;
    @EJB
    private StaffEJB staffEJB;
    
    private static Admin admin = new Admin();
    private static Customer customer = new Customer();
    private static Staff staff = new Staff();
            

    public String doEditEntity(Long id, String entityName) {
        switch (entityName) {
            case "admin":
                admin = adminEJB.getAdmin(id);
                return "editAdmin.xhtml?faces-redirect=true";
            case "customer":
                customer = customerEJB.getCustomer(id);
                return "editCustomer.xhtml?faces-redirect=true";
            case "staff":
                staff = staffEJB.getStaff(id);
                return "editStaff.xhtml?faces-redirect=true";
            default:
                return null;
        }
    }

    public String doEditEntityCommit(String entityName) {
        switch (entityName){
            case "admin":
               
                if (adminEJB.editAdminCommit(admin) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listAdmins.xhtml?faces-redirect=true";
                }
            case "customer":
                
                if (customerEJB.editCustomerCommit(customer) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listCustomers.xhtml?faces-redirect=true";
                }
            case "staff":
                
                if (staffEJB.editStaffCommit(staff) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listStaffs.xhtml?faces-redirect=true";
                }
            default:
                return null;
        }
    }
    
    public String doCancelEdit(String entityName){
        switch(entityName){
            case "admin":
                return "listAdmins.xhtml?faces-redirect=true";
            case "customer":
                return "listCustomers.xhtml?faces-redirect=true";
            case "staff":
                return "listStaffs.xhtml?faces-redirect=true";
            default:
                return null;
        }
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        entityEditController.admin = admin;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        entityEditController.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        entityEditController.staff = staff;
    }
}
