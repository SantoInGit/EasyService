package controllers;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ejb.ServiceOrderEJB;
import entities.ServiceOrder;

@Named(value = "serviceOrderController")
@RequestScoped
public class ServiceOrderController {

    public ServiceOrderController() {
    }
    @EJB
    private ServiceOrderEJB serviceOrderEJB;
    private ServiceOrder serviceOrder = new ServiceOrder();
    
    private int service_id;
    private String service_name;
    private String customer_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
    
    
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String doCreateServiceOrder() {

        serviceOrder = serviceOrderEJB.addServiceOrder(serviceOrder, service_id, service_name, customer_id);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Order Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "frontendCustomerProfile.xhtml?faces-redirect=true";
    }

}
