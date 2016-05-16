
package controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Service;
import ejb.ServiceEJB;

import entities.ServiceCategory;
import java.util.ArrayList;
import ejb.ServiceCategoryEJB;


@Named(value = "serviceController")
@RequestScoped
public class ServiceController {

    public ServiceController() {
    }
    @EJB
    private ServiceEJB serviceEJB;
    private List<ServiceCategory> serviceCategoryList = new ArrayList<>();
    
    private Service service = new Service();

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    private List<Service> serviceList = new ArrayList<>();
   
    private String search = "";
    private String searchBy = "";

    /**
     *
     * @return String
     */
    public String doCreateService() {
       
        service = serviceEJB.addService(service);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listService.xhtml";
    }

    /**
     *
     * @return
     */
    public String doSearch() {
        serviceList = serviceEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listService.xhtml";
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

    
    public Service getStaffById(Long serCat_id) {
        service = serviceEJB.getService(serCat_id);
        return service;
    }

    /**
     *
     * @return
     */
    public Service getServiceByParamId() {
        service = serviceEJB.getServiceByParamId();
        return service;
    }

    /**
     *
     * @return
     */
    public List<Service> getServiceList() {
        if (this.search.isEmpty()) {
            serviceList = serviceEJB.listService();
        }
        return serviceList;
    }

    /**
     *
     * @param serviceList
     */
    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<ServiceCategory> getServiceCategoryList() {
        ServiceCategoryEJB serCatEjb = new ServiceCategoryEJB();
        serviceCategoryList = serCatEjb.listServiceCategory();
        return serviceCategoryList;
    }

    public void setServiceCategory(List<ServiceCategory> serviceCategoryList) {
        this.serviceCategoryList = serviceCategoryList;
    }

  

}
