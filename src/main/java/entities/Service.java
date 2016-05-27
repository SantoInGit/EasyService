/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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

/**
 *
 * @author Santo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllService", query = "select s from Service s"),
    @NamedQuery(name = "findServiceByServiceName", query = "select s from Service s where UPPER(s.serviceName) LIKE :ServiceName"),
    @NamedQuery(name = "findServiceByStatus", query = "select s from Service s where UPPER(s.status) LIKE :Status"),
//update query
    @NamedQuery(name = "updateService", 
            query="UPDATE Service a SET  "
                    + "a.description = :serDescription, "
                    + "a.ratePerHour = :serRatePerHour, "
                    + "a.serviceName = :serServiceName, "
                    + "a.status = :serStatus " 
                    + "WHERE a.serviceId = :serId")        
       
})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String serviceName;
    private Float ratePerHour;
    private String description;
    private String status;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinTable(
            name="jnd_Service_ServiceCategory",
            joinColumns=@JoinColumn(name="service_serviceCategory_fk")
    )
    private ServiceCategory serviceCategory;
    


    public Service() {
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

   
    

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Service[ id=" + serviceId + " ]";
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Float getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(Float ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
