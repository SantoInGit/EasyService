package controllers;

import ejb.AdminEJB;
import ejb.CustomerEJB;
import ejb.ServiceCategoryEJB;
import ejb.ServiceEJB;
import ejb.StaffEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Admin;
import entities.Customer;
import entities.Service;
import entities.ServiceCategory;
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
    @EJB
    private ServiceEJB serviceEJB;
    @EJB
    private ServiceCategoryEJB serviceCategoryEJB;
    
    
    private static Admin admin = new Admin();
    private static Customer customer = new Customer();
    private static Staff staff = new Staff();
    private static Service service = new Service();
    private static ServiceCategory serviceCategory = new ServiceCategory();


            

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
            case "service":
                service = serviceEJB.getService(id);
                return "editService.xhtml?faces-redirect=true";
            case "serviceCategory":
                serviceCategory = serviceCategoryEJB.getServiceCategory(id);
                return "editServiceCategory.xhtml?faces-redirect=true";
            default:
                return null;
        }
    }

    public String doEditEntityCommit(String entityName) {
        switch (entityName.toLowerCase()){
            case "admin":
               
                if (adminEJB.editAdminCommit(admin) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listAdmins.xhtml?faces-redirect=true";
                }
            case "customer":
                
                if (customerEJB.editCustomerCommit(customer) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "customer Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listCustomers.xhtml?faces-redirect=true";
                }
            case "staff":
                
                if (staffEJB.editStaffCommit(staff) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listStaffs.xhtml?faces-redirect=true";
                }
                case "service":
                
                if (serviceEJB.editServiceCommit(service) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listService.xhtml?faces-redirect=true";
                }
                case "servicecategory":
                
                if (serviceCategoryEJB.editServiceCategoryCommit(serviceCategory) >= 0) {
                    FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Category Edited", "");
                    FacesContext.getCurrentInstance().addMessage(null, infoMsg);
                    return "listServiceCategory.xhtml?faces-redirect=true";
                }
            default:
                return null;
        }
    }
    
    public String doCancelEdit(String entityName){
        switch(entityName.toLowerCase()){
            case "admin":
                return "listAdmins.xhtml?faces-redirect=true";
            case "customer":
                return "listCustomers.xhtml?faces-redirect=true";
            case "staff":
                return "listStaffs.xhtml?faces-redirect=true";
            case "service":
                return "listService.xhtml?faces-redirect=true";
            case "servicecategory":
                return "listServiceCategory.xhtml?faces-redirect=true";
            default:
                return null;
        }
    }
    
    public String doVeiwDetails(Long id, String entityName) {
        switch (entityName) {
            case "admin":
                admin = adminEJB.getAdmin(id);
                return "viewAdmin.xhtml?faces-redirect=true";
            case "customer":
                customer = customerEJB.getCustomer(id);
                return "viewCustomer.xhtml?faces-redirect=true";
            case "staff":
                staff = staffEJB.getStaff(id);
                return "viewStaff.xhtml?faces-redirect=true";
            case "service":
                service = serviceEJB.getService(id);
                return "viewService.xhtml?faces-redirect=true";
            case "serviceCategory":
                serviceCategory = serviceCategoryEJB.getServiceCategory(id);
                return "viewServiceCategory.xhtml?faces-redirect=true";
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
        public  Service getService() {
        return service;
    }

    public void setService(Service service) {
        entityEditController.service = service;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        entityEditController.serviceCategory = serviceCategory;
    }
    
}