
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Named queries for getting all customers with specified parameter
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllCustomer", query = "select c from Customer c"),
    @NamedQuery(name = "findCustomerByFirstName", query = "select c from Customer c where UPPER(c.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findCustomerByLastName", query = "select c from Customer c where UPPER(c.lastName) LIKE :LastName"),
    @NamedQuery(name = "findCustomerByAddress", query = "select c from Customer c where UPPER(c.address.street) LIKE :Address"),
    @NamedQuery(name = "findCustomerById", query = "select c from Customer c where c.id=:id")
})
/**
 * customer entity class
 */
public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jnd_Customer_ServiceOrder",
            joinColumns = @JoinColumn(name = "customer_serviceOrder_fk")
    )
    private List<ServiceOrder> serviceOrder;
    
    public Customer(){
    
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
