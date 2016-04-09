/**
 * @author jagatrp<Jagat Ram Prajapati>
 * @email prajapatijagat2009@gmail.com
 */
package rims.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNo;
    private String email;
    private String password;

    /**
     * added one to many unidirectional relation from customer to rent;
     */

    /**
     * default constructor
     */
    public Customer() {
    }

    /**
     * constructor to set firstName and lastName
     *
     * @param firstName
     * @param lastName
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * constructor to set firstName,lastName,address,rents
     *
     * @param firstName
     * @param lastName
     * @param address
     */
    public Customer(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     *
     * @return
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * to get PhoneNo of customer
     *
     * @return
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * to set PhoneNo of customer
     *
     * @param phoneNo
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * to get email address of customer
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * to set email address of customer
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * to get customer Id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * to get customer address
     *
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     * to get customer firstName
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * to get customer lastName
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * to set address for customer
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * to set firstName for customer
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * to set lastName for customer
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   


}
