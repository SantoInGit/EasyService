/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Santo
 */
@Entity
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceOrderId;
    private String customerNote;
    private String hoursPerDay;
    @Temporal(TemporalType.DATE)
    private Date serviceOrderDate;
    private String fromDate;
    private String toDate;
    private String serviceOrderStatus;
    private int customer_id;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "jnd_ServiceOrder_ServiceOrderItem",
            joinColumns = @JoinColumn(name = "ServiceOrder_ServiceOrderItem_fk")
    )
    private List<ServiceOrderItem> serviceOrderItem;

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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }



    public Date getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(Date serviceOrderDate) {
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
