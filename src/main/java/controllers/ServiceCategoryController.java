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

/**
 *
 * A request scoped controller to handle all the operation related to service category
 */
@Named(value = "serviceCategoryController")
@RequestScoped
public class ServiceCategoryController {

    /**
     * Default constructor
     */
    public ServiceCategoryController() {
    }
    //EJB injection
    @EJB
    private ServiceCategoryEJB serviceCategoryEJB;
    private ServiceCategory serviceCategory = new ServiceCategory();
    private List<ServiceCategory> serviceCategoryList = new ArrayList<>();
    private List<ServiceCategory> serviceCategoryFrontendList = new ArrayList<>();
    private String search = "";
    private String searchBy = "";

    /**
     *
     * @return
     */
    public List<ServiceCategory> getServiceCategoryFrontendList() {
        serviceCategoryFrontendList = serviceCategoryEJB.listFrontendServiceCategory();

        return serviceCategoryFrontendList;
    }

    /**
     *
     * @param serviceCategoryFrontendList
     */
    public void setServiceCategoryFrontendList(List<ServiceCategory> serviceCategoryFrontendList) {
        this.serviceCategoryFrontendList = serviceCategoryFrontendList;
    }

    /**
     * Function to delete service category from the database
     * @param id to be passed to find serviceCategory object
     * @return string: name of the page to be rendered
     */
    public String doDeleteServiceCategory(Long id) {
        serviceCategoryEJB.deleteServiceCategory(id);
        return "listServiceCategory.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return serviceCategory object
     */
    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    /**
     *
     * @param serviceCategory to set attribute serviceCategory
     */
    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    /**
     *
     * @return searchBy 
     */
    public String getSearchBy() {
        return searchBy;
    }

    /**
     *
     * @param searchBy to set attribute searchBy
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    /**
     *
     * @return search
     */
    public String getSearch() {
        return search;
    }

    /**
     *
     * @param search to set the attribute search
     */
    public void setSearch(String search) {
        this.search = search;
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

    /**
     * Function to create a service category object and persist it to the database
     * @return string: name of the page to be rendered
     */
    public String doCreateServiceCategory() {

        serviceCategory = serviceCategoryEJB.addServiceCategory(serviceCategory);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Category Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceCategory.xhtml?faces-redirect=true";
    }

    /**
     * Function to perform search of service category
     * @return string: name of the page to be rendered
     */
    public String doSearch() {
        serviceCategoryList = serviceCategoryEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceCategory.xhtml?faces-redirect=true";
    }

}
