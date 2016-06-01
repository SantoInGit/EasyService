package controllers;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ejb.ServiceOrderEJB;
import entities.Customer;
import entities.ServiceOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;

@Named(value = "serviceOrderController")
@RequestScoped
public class ServiceOrderController {

    public ServiceOrderController() {
    }
    @EJB
    private ServiceOrderEJB serviceOrderEJB;
    private static ServiceOrder serviceOrder = new ServiceOrder();
    private List<ServiceOrder> serviceOrderList = new ArrayList<>();
    private List<ServiceOrder> frontendServiceOrderList = new ArrayList<>();
    private List<String> staffid = new ArrayList<>();

    public List<String> getStaffid() {
        return staffid;
    }

    public List<ServiceOrder> getFrontendServiceOrderList() {
//        MailController sender = new MailController();
//        sender.sendSimpleMail();
       // GmailSender sender = new GmailSender();

//        try {
//            sender.setSender("info@noriskwebsolutions.com", "jagat@123");
//            sender.addRecipient("prajapatijagat2009@gmail.com");
//            sender.setSubject("The subject");
//            sender.setBody("The body");
//            // sender.addAttachment("TestFile.txt");
//            sender.send();
//        } catch (MessagingException ex) {
//            Logger.getLogger(ServiceOrderController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = extContext.getSessionMap();
        Customer cst = (Customer) sessionMap.get("customer");

        frontendServiceOrderList = serviceOrderEJB.customerListServiceOrders(cst.getId());
        return frontendServiceOrderList;
    }

    public void setFrontendServiceOrderList(List<ServiceOrder> frontendServiceOrderList) {
        this.frontendServiceOrderList = frontendServiceOrderList;
    }

    public void setStaffid(List<String> staffid) {
        this.staffid = staffid;
    }
    private String search = "";
    private String searchBy = "";

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
        ServiceOrderController.serviceOrder = serviceOrder;
    }

    public List<ServiceOrder> getServiceOrderList() {
        if (this.search.isEmpty()) {
            serviceOrderList = serviceOrderEJB.listServiceOrders();
        }
        return serviceOrderList;
    }

    public void setServiceOrderList(List<ServiceOrder> serviceOrderList) {
        this.serviceOrderList = serviceOrderList;
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

    public String doCreateServiceOrder() {

        serviceOrder = serviceOrderEJB.addServiceOrder(serviceOrder, service_id, service_name, customer_id);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Order Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "frontendCustomerProfile.xhtml?faces-redirect=true";
    }

    public String doDeleteServiceOrder(Long id) {
        serviceOrderEJB.deleteServiceOrder(id);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doCreateInvoice(Long id) {
        serviceOrderEJB.createInvoice(id);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doChangeStatusServiceOrder(Long id, String status) {
        serviceOrderEJB.changeStatusServiceOrder(id, status);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String frontendDoChangeStatusServiceOrder(Long id, String status) {
        serviceOrderEJB.changeStatusServiceOrder(id, status);
        return "customerMyOrders.xhtml?faces-redirect=true";
    }
    
     public String doRescheduleServiceOrder(Long id){
         serviceOrder = serviceOrderEJB.rescheduleServiceOrder(id);
         return "cutomerRescheduleOrder.xhtml?faces-redirect=true";
     }
     
     public String doRescheduleServiceOrderCommit(Long id){
         serviceOrder = serviceOrderEJB.rescheduleServiceOrderCommit(serviceOrder,id);
         return "customerMyOrders.xhtml";
     }

    public String doConfirmServiceOrder(Long id) {
        serviceOrder = serviceOrderEJB.confirmServiceOrder(id);
        return "confirmServiceOrder.xhtml?faces-redirect=true";
    }

    public String doConfirmServiceOrderCommit(Long id) {
        serviceOrder = serviceOrderEJB.confirmServiceOrderCommit(id, staffid);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doSearch() {
        serviceOrderList = serviceOrderEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceOrders.xhtml";
    }

    public int doSearchServiceOrderByStatusForDashboard(String status, String searchBy) {
        return serviceOrderEJB.search(status, searchBy).size();
    }

}
