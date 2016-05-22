
package entities;

/*
An embeddable class to be embedded in Customer class for address
 */
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

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
    public Address(){
        
    }
    //Contructor
    public Address(String street, String city, String state,String zipCode, String country){
        this.setStreet(street);
        this.setCity(city);
        this.setState(state);
        this.setZipCode(zipCode);
        this.setCountry(country);
    }
    
    //getter functions
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
     @Column(name = "STATE")
    public String getState() {
        return state;
    }
    @Column(name = "ZIPCODE")
    public String getZipCode() {
        return zipCode;
    }
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    //setter functions
    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return  "street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", country=" + country ;
    }
    
    
}
