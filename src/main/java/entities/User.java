/*
 User class to be inherited by Customer, Staff and Admin class
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;


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
     
    public User(){
        this.address = new Address();
    }

    //getter and setter functions
    //get and set id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    //get and set first name
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    //get and set middle name
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    //get and set last name
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    //get and set address
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    
    //get and set phone number
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    //get and set email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    //get and set password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    //get and set user type
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
