/*
 User class to be inherited by Customer, Staff and Admin class
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * A mapped super class to represent as an abstract class. This class is inherited
 * by Admin, Staff and Customer classes
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    @Embedded
    private Address address;
    private String phoneNo;
    private String email;
    private String password;
    private String userType;
    
    //constructor

    /**
     * Default Constructor
     */
     
    public User(){
        this.address = new Address();
    }

    //getter and setter functions
    //get and set id

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id to set the attribute id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    //get and set first name

    /**
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName to set the attribute firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    //get and set middle name

    /**
     *
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     *
     * @param middleName to set the attribute middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    //get and set last name

    /**
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName to set the attribute lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    //get and set address

    /**
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address to set the attribute address
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    //get and set phone number

    /**
     *
     * @return phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     *
     * @param phoneNo to set the attribute phoneNo
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    //get and set email

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email to set the attribute email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    //get and set password

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password to set the attribute password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    //get and set user type

    /**
     *
     * @return userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType to set the attribute userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
