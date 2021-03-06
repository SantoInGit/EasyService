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

/**
 *
 * An entity class to represent a service order item object
 */
@Entity
public class ServiceOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;
    private String orderItemName;
    private String orderItemRate;
    private String serviceStatus;

    //constructor

    /**
     *Default constructor
     */
    public ServiceOrderItem() {
    }   
    
    //getter and setter fucntions
    //get and set order item id

    /**
     *
     * @return orderItemId
     */
    public Long getOrderItemId() {
        return orderItemId;
    }

    /**
     *
     * @param orderItemId to set the attribute orderItemId
     */
    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     *
     * @return orderItemRate
     */
    public String getOrderItemRate() {
        return orderItemRate;
    }

    /**
     *
     * @param orderItemRate to set the attribute orderItemRate
     */
    public void setOrderItemRate(String orderItemRate) {
        this.orderItemRate = orderItemRate;
    }
    
    //get and set order item name

    /**
     *
     * @return to set the attribute orderItemName
     */
    public String getOrderItemName() {
        return orderItemName;
    }

    /**
     *
     * @param orderItemName to set the attribute orderItemName
     */
    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    //get and set the status of the order item

    /**
     *
     * @return serviceStatus
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /**
     *
     * @param serviceStatus to set the attribute serviceStatus
     */
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
        return orderItemName+","+orderItemRate;
    }       
}
