/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
    @NamedQuery(name = "findAllServiceOrders", 
            query = "select c from ServiceOrder c"),   
    @NamedQuery(name = "findServiceOrdersByCustomer", query = "select so from ServiceOrder so JOIN so.Customer c where c.id = :CID "),
    @NamedQuery(name = "changeServiceOrderStatus",
            query="update ServiceOrder s Set s.serviceOrderStatus = :status WHERE s.serviceOrderId = :serOrderId")      
})
public class ServiceOrder implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceOrderId;
    private String customerNote;
    private String hoursPerDay;
    private String serviceOrderDate;
    private String fromDate;
    private String toDate;
    private String staffRequired;
    private String serviceOrderStatus;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "jnd_ServiceOrder_ServiceOrderItem",
            joinColumns = @JoinColumn(name = "ServiceOrder_ServiceOrderItem_fk")
    )
    private List<ServiceOrderItem> serviceOrderItem;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinTable(
            name="jnd_ServiceOrder_Customer",
            joinColumns=@JoinColumn(name="serviceOrder_Customer_fk")
    )
    private Customer Customer;

    public String getStaffRequired() {
        return staffRequired;
    }

    public void setStaffRequired(String staffRequired) {
        this.staffRequired = staffRequired;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public ServiceOrder() {
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(String hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public String getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(String serviceOrderDate) {
        this.serviceOrderDate = serviceOrderDate;
    }

    public String getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(String serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public List<ServiceOrderItem> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItem> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderId != null ? serviceOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.serviceOrderId == null && other.serviceOrderId != null) || (this.serviceOrderId != null && !this.serviceOrderId.equals(other.serviceOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ServiceOrder[ id=" + serviceOrderId + " ]";
    }

}
