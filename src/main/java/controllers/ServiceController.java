package controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Service;
import ejb.ServiceEJB;

import java.util.ArrayList;

@Named(value = "serviceController")
@RequestScoped
public class ServiceController {

    public ServiceController() {
    }
    @EJB
    private ServiceEJB serviceEJB;
    private String serviceCategory;

    private Service service = new Service();
    private List<Service> serviceList = new ArrayList<>();

    private String search = "";
    private String searchBy = "";
    
    public String doCreateService() {
        service = serviceEJB.addService(service, serviceCategory);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listService.xhtml?faces-redirect=true";
    }

    public String doDeleteService(Long id){
        serviceEJB.deleteService(id);
        return "listService.xhtml?faces-redirect=true";
    }
    
    public String doSearch() {
        serviceList = serviceEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listService.xhtml?faces-redirect=true";
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

    public Service getServiceByParamId() {
        service = serviceEJB.getServiceByParamId();
        return service;
    }

    public List<Service> getServiceList() {
        if (this.search.isEmpty()) {
            serviceList = serviceEJB.listService();
        }
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

      
    public String doCreateServiceOrder(int service_id){
        CustomerLogInOutController customer = new CustomerLogInOutController();
        boolean isLoggedIn = customer.isLoggedIn();
        if(isLoggedIn){
            return "index.xhtml";
        }
        return "login.xhtml";
    }
    
}
