package controllers;

import java.io.*;
import java.util.*;
import com.pdfjet.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Service;
import ejb.ServiceEJB;
//import com.pdfjet.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    private List<Service> serviceListLimitFour = new ArrayList<>();

    private String search = "";
    private String searchBy = "";

    public List<Service> getServiceListLimitFour() {
        serviceListLimitFour = serviceEJB.listServiceLimitFour();
        return serviceListLimitFour;
    }

    public void setServiceListLimitFour(List<Service> serviceListLimitFour) {
        this.serviceListLimitFour = serviceListLimitFour;
    }

    public String doCreateService() {
        service = serviceEJB.addService(service, serviceCategory);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listService.xhtml?faces-redirect=true";
    }

    public String doDeleteService(Long id) {
        serviceEJB.deleteService(id);
        return "listService.xhtml?faces-redirect=true";
    }

    public String doSearch() {
        serviceList = serviceEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);

        return "listService.xhtml";

    }

    public String doFrontEndSearch() {
        return "frontendListServices.xhtml?faces-redirect=true&search_by=" + searchBy + "&s=" + search;
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
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String search_by = params.get("search_by");
            String search_text = params.get("s");
            if (search_by != null && search_text != null) {
                serviceList = serviceEJB.searchFrontend(search_text, search_by);
                FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
                FacesContext.getCurrentInstance().addMessage(null, infoMsg);
            } else {
                serviceList = serviceEJB.listService();
            }
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


}
