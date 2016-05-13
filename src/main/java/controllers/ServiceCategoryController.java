
package controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.ServiceCategory;
import ejb.ServiceCategoryEJB;
import java.util.ArrayList;


@Named(value = "serviceCategoryController")
@RequestScoped
public class ServiceCategoryController {

    public ServiceCategoryController() {
    }
    @EJB
    private ServiceCategoryEJB serviceCategoryEJB;
    private ServiceCategory serviceCategory = new ServiceCategory();

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
    private List<ServiceCategory> serviceCategoryList = new ArrayList<>();
    private String search = "";
    private String searchBy = "";

    /**
     *
     * @return String
     */
    public String doCreateServiceCategory() {
       
        serviceCategory = serviceCategoryEJB.addServiceCategory(serviceCategory);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Category Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceCategory.xhtml";
    }

    /**
     *
     * @return
     */
    public String doSearch() {
        serviceCategoryList = serviceCategoryEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceCategory.xhtml";
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

    
    public ServiceCategory getStaffById(Long serCat_id) {
        serviceCategory = serviceCategoryEJB.getServiceCategory(serCat_id);
        return serviceCategory;
    }

    /**
     *
     * @return
     */
    public ServiceCategory getServiceCategoryByParamId() {
        serviceCategory = serviceCategoryEJB.getServiceCategoryByParamId();
        return serviceCategory;
    }

    /**
     *
     * @return
     */
    public List<ServiceCategory> getServiceCategoryList() {
        if (this.search.isEmpty()) {
            serviceCategoryList = serviceCategoryEJB.listServiceCategory();
        }
        return serviceCategoryList;
    }

    /**
     *
     * @param serviceCategoryList
     */
    public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
        this.serviceCategoryList = serviceCategoryList;
    }

  

}
