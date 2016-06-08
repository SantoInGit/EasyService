
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * An entity class to represent an invoice object
 */
@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jnd_Invoice_ServiceOrder",
            joinColumns = @JoinColumn(name = "Invoice_ServieOrder_fk")
    )
    private ServiceOrder serviceOrder;
    private Float price;

    /**
     * Default constructor
     */
    public Invoice() {
    }
    
    /**
     *
     * @return invoiceId
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     *
     * @param invoiceId to set the attribute invoiceId
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     *
     * @return invoiceDate
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     *
     * @param invoiceDate to set the attribute invoiceDate
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     *
     * @return serviceOrder
     */
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    /**
     *
     * @param serviceOrder to set the attribute serviceOrder 
     */
    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    /**
     *
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     *
     * @param price to set the attribute price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Invoice[ id=" + invoiceId + " ]";
    }

    
    
}
