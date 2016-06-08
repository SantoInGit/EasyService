/*
Entity class for  Service 
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
 * An entity class to represent a service object
 */
@Entity
@NamedQueries({
    //select queries for service search
    @NamedQuery(name = "findAllService", query = "select s from Service s"),
    @NamedQuery(name = "findAllActiveService", query = "select s from Service s where UPPER(s.status) = :status"),
    @NamedQuery(name = "findServiceByServiceName", query = "select s from Service s where UPPER(s.serviceName) LIKE :ServiceName"),
    @NamedQuery(name = "findServiceByFrontendServiceName", query = "select s from Service s where UPPER(s.serviceName) LIKE :ServiceName AND UPPER(s.status) = :status"),
    @NamedQuery(name = "findServiceByFrontendCategory", query = "select s from Service s JOIN s.serviceCategory sc where sc.serviceCategoryId = :Category AND UPPER(s.status) = :status"),
    @NamedQuery(name = "findServiceByStatus", query = "select s from Service s where UPPER(s.status) LIKE :Status"),
    //update query for service update
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
    

    //constrcutoer

    /**
     * Default constructor
     */
    public Service() {
    }

    //getter and setter functions
    //get and set service category

    /**
     *
     * @return serviceCategory
     */
    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    /**
     *
     * @param serviceCategory to set the attribute serviceCategory
     */
    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
    
    //get and set service id

    /**
     *
     * @return serviceId
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     *
     * @param serviceId to set the attribute serviceId
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    //get and set for service name

    /**
     *
     * @return serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     *
     * @param serviceName to set the attribute serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    //get and set function for rate per hour

    /**
     *
     * @return ratePerHour
     */
    public Float getRatePerHour() {
        return ratePerHour;
    }

    /**
     *
     * @param ratePerHour to set the attribute ratePerHour
     */
    public void setRatePerHour(Float ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    //get and set function for service description

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description to set the attribute description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //get and set function for service status

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status to set the attribute status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    //auto generated codes
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

    
}
