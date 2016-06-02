/**
 * Customer entity class
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    //select queries for customers
    @NamedQuery(name = "findAllCustomer", query = "select c from Customer c"),
    @NamedQuery(name = "findCustomerByFirstName", query = "select c from Customer c where UPPER(c.firstName) LIKE :FirstName"),
    @NamedQuery(name = "findCustomerByLastName", query = "select c from Customer c where UPPER(c.lastName) LIKE :LastName"),
    @NamedQuery(name = "findCustomerByAddress", query = "select c from Customer c where UPPER(c.address.street) LIKE :Address"),
    @NamedQuery(name = "findCustomerById", query = "select c from Customer c where c.id=:id"),
    @NamedQuery(name = "findCustomerByEmailAndPassword", query = "select c from Customer c where c.email=:email and c.password = :password"),
    //update query for customer
    @NamedQuery(name = "updateCustomer", //query = "update Admin a Set a.email=?1, a.firstName=?2 Where a.id = ?3")
            query = "update Customer a Set  a.email = :custEmail, a.firstName = :custFirstName, a.lastName = :custLastName, "
            + "a.middleName = :custMiddleName, a.password = :custPassword, a.phoneNo = :custPhoneNo, "
            + "a.status = :custStatus, a.userType = :custUserType, a.address.city = :custCity, a.address.country = :custCountry, "
            + "a.address.state = :custState, a.address.street = :custStreet, a.address.zipCode = :custZipCode "
            + "where a.id = :custId")
})

public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;

    //constructors
    
    public Customer() {

    }

    //getter and setter functions
    
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
