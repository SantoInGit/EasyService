
package controllers;

import ejb.ServiceOrderEJB;
import ejb.RateNFeedBackEJB;
import entities.RateNFeedBack;
import entities.ServiceOrder;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named(value = "rateNFeedbackController")
@RequestScoped
public class RateNFeedbackController {
    public RateNFeedbackController(){
        
    }
    
    @EJB
    ServiceOrderEJB servOrderEJB;
    @EJB
    RateNFeedBackEJB rateNFeedbackEJB;
    private static ServiceOrder serviceOrder = new ServiceOrder();

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public RateNFeedBack getRateNFeedback() {
        return rateNFeedback;
    }

    public void setRateNFeedback(RateNFeedBack rateNFeedback) {
        this.rateNFeedback = rateNFeedback;
    }
    private RateNFeedBack rateNFeedback = new RateNFeedBack();
    
    public String doCreateRateNFeedback(Long id){
        serviceOrder = rateNFeedbackEJB.createRateNFeedback(id);
        return "rateNFeedback.xhtml?faces-redirect=true";
    }
    public String doCreateRateNFeedbackCommit(){
        rateNFeedback.setCustomerName(serviceOrder.getCustomer().getFirstName());
        rateNFeedback.setServiceOrderId(serviceOrder.getServiceOrderId());
        rateNFeedback = rateNFeedbackEJB.createRateNFeedbackCommit(rateNFeedback);
        return "customerMyOrders.xhtml?faces-redirect=true";
    }
    
}
