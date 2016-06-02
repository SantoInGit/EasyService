/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ServiceOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;
    private String orderItemName;
    private String serviceStatus;

    //constructor
    public ServiceOrderItem() {
    }   
    
    //getter and setter fucntions
    //get and set order item id
    public Long getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
    
    //get and set order item name
    public String getOrderItemName() {
        return orderItemName;
    }
    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    //get and set the status of the order item
    public String getServiceStatus() {
        return serviceStatus;
    }
    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    
    //auto generated codes
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderItemId != null ? orderItemId.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderItem)) {
            return false;
        }
        ServiceOrderItem other = (ServiceOrderItem) object;
        if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return orderItemName;
    }       
}
