
package entities;

/*
An embeddable class to be embedded in Customer class for address
 */
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * An embeddable class to be used in Customer, Admin and Staff classes using @Embedded tag
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable
{
     
     private String street;
    
     private String city;
    
     private String state;
     
     private String zipCode;
     
     private String country;
    
     //Constructor

    /**
     * Default constructor
     */
    public Address(){
        
    }
    //Contructor

    /**
     * Parameterized constructor
     * @param street to set the attribute street
     * @param city to set the attribute city
     * @param state to set the attribute state
     * @param zipCode to set the attribute zipCode
     * @param country to set the attribute country
     */
    public Address(String street, String city, String state,String zipCode, String country){
        this.setStreet(street);
        this.setCity(city);
        this.setState(state);
        this.setZipCode(zipCode);
        this.setCountry(country);
    }
    
    //getter functions

    /**
     *
     * @return street
     */
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    /**
     *
     * @return city
     */
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    /**
     *
     * @return state
     */
    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    /**
     *
     * @return zipCode
     */
    @Column(name = "ZIPCODE")
    public String getZipCode() {
        return zipCode;
    }

    /**
     *
     * @return country
     */
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    //setter functions

    /**
     *
     * @param street to set the attribute street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @param city to set the attribute city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param state to set the attribute state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @param zipCode to set the attribute zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     *
     * @param country to set the attribute country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return  "street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", country=" + country ;
    }
    
    
}
