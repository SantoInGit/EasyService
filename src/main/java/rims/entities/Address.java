/**
 * @author jagatrp<Jagat Ram Prajapati>
 * @email prajapatijagat2009@gmail.com
 */
package rims.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * Embedded address field for customer.
 */
@Embeddable
public class Address implements Serializable {

    private String city;
    private String zipcode;
    private String country;
    private String street;

    public Address() {
    }

    /**
     *
     * @param city
     * @param zipcode
     * @param country
     * @param street
     */
    public Address(String city, String zipcode, String country, String street) {
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
        this.street = street;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * to get Zipcode
     *
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * set Country for customer
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * to get Street
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     * to set city
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * to set zipcode
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * to set country for customer
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * to set street
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

}
