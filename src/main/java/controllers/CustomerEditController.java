package controllers;

import ejb.CustomerEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named(value = "customerEditController")
@RequestScoped
public class CustomerEditController implements Serializable {

    public CustomerEditController() {
    }

    @EJB
    private CustomerEJB customerEJB;
    private static Customer customer = new Customer();

    public String doEditCustomer(Long id) {
        customer = customerEJB.getCustomer(id);
        return "editCustomer.xhtml?faces-redirect=true";
    }

    public String doEditCustomerCommit() {

        int a = customerEJB.editCustomerCommit(customer);
        if (a >= 0) {
            FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Edited", "");
            FacesContext.getCurrentInstance().addMessage(null, infoMsg);
           
        }

        return "listCustomers.xhtml?faces-redirect=true";
    }
    public String doCancelEditCustomer(){
        return "listCustomers.xhtml?faces-redirect=true";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        CustomerEditController.customer = customer;
    }
}
