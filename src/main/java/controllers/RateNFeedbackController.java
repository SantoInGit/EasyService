
package controllers;

import ejb.ServiceOrderEJB;
import ejb.RateNFeedBackEJB;
import entities.RateNFeedBack;
import entities.ServiceOrder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * A request scoped controller  to handle operation on rate and feedback object
 */
@Named(value = "rateNFeedbackController")
@RequestScoped
public class RateNFeedbackController {

    /**
     * Default constructor
     */
    public RateNFeedbackController(){
        
    }
    
    //EJB injections
    @EJB
    ServiceOrderEJB servOrderEJB;
    @EJB
    RateNFeedBackEJB rateNFeedbackEJB;
    private static ServiceOrder serviceOrder = new ServiceOrder();
    private List<RateNFeedBack> ratesNFeedbacksList = new ArrayList<>() ;

    /**
     * Function to return list of all rate and feedback objects
     * @return list of rate and feedback objects
     */
    public List<RateNFeedBack> getRatesNFeedbacksList() {
        ratesNFeedbacksList =  rateNFeedbackEJB.listRatesAndFeedbacks();
        return ratesNFeedbacksList;
    }

    /**
     *
     * @param RatesNFeedbacksList to set attribute RatesNFeedbackList
     */
    public void setRatesNFeedbacksList(List<RateNFeedBack> RatesNFeedbacksList) {
        this.ratesNFeedbacksList = RatesNFeedbacksList;
    }

    /**
     * Function to return serviceOrder
     * @return
     */
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    /**
     *
     * @param serviceOrder to set the attribute serviceOrder
     */
    public void setServiceOrder(ServiceOrder serviceOrder) {
        RateNFeedbackController.serviceOrder = serviceOrder;
    }

    /**
     *
     * @return rate and feedback object
     */
    public RateNFeedBack getRateNFeedback() {
        return rateNFeedback;
    }

    /**
     *
     * @param rateNFeedback to set attribute rateNFeedback
     */
    public void setRateNFeedback(RateNFeedBack rateNFeedback) {
        this.rateNFeedback = rateNFeedback;
    }
    private RateNFeedBack rateNFeedback = new RateNFeedBack();
    
    /**
     *
     * @param id
     * @return
     */
    public String doCreateRateNFeedback(Long id){
        serviceOrder = rateNFeedbackEJB.createRateNFeedback(id);
        return "rateNFeedback.xhtml?faces-redirect=true";
    }

    /**
     * Function to create a rate and feed back object
     * @return string: name of the page to be rendered
     */
    public String doCreateRateNFeedbackCommit(){
        rateNFeedback.setCustomerName(serviceOrder.getCustomer().getFirstName());
        rateNFeedback.setServiceOrderId(serviceOrder.getServiceOrderId());
        rateNFeedback = rateNFeedbackEJB.createRateNFeedbackCommit(rateNFeedback);
        return "customerMyOrders.xhtml?faces-redirect=true";
    }
    
    /**
     * Function to delete rate and feedback object
     * @param id to be passed to find the rate and feedback object
     * @return string: name of the page to be rendered
     */
    public String doDeleteRate(Long id){
        rateNFeedbackEJB.deleteRate(id);
        return "listRateNFeedback.xhtml?faces-redirect=true";
    }
    
}
