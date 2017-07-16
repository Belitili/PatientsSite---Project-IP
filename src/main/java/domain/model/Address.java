package domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
/**
 *
 * @author Marie
 */
@Entity
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "address_seq_gen")
    @SequenceGenerator(name="address_seq_gen", sequenceName="ADDRESS_SEQ")
    private Long id; 
     
    @Column(name = "street", nullable = false)
    private String street;
     
    @Column(name = "houseNumber", nullable = false)
    private int houseNumber;
    
    @Column(name = "addOn", nullable = true)
    private String addOn;
     
    @Column(name = "zipCode", nullable = false)
    private String zipCode;
     
    @Column(name = "city", nullable = false)
    private String city;
     
    @Column(name = "state", nullable = true)
    private String state;
    
    @Column(name = "country", nullable = false)
    private String country;

    Address(String street, int houseNumber, String zipCode, String city, String country) {
        this.setValues(street, houseNumber, null, zipCode, city, null, country);
    }

    Address(String street, int houseNumber, String addOn, String zipCode, String city, String state, String country) {
        this.setValues(street, houseNumber, addOn, zipCode, city, state, country);
    }
    
    private void setValues(String street, int houseNumber, String addOn, String zipCode, String city, String state, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.addOn = addOn;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getZipCode() {
        return zipCode;
    }
 
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddOn() {
        return addOn;
    }

    public void setAddOn(String addOn) {
        this.addOn = addOn;
    }
 
    public int getHouseNumber() {
        return houseNumber;
    }
         
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
 
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        this.street = street;
    }
    
    @Override
    public String toString() {
        String st = "";
        if (getState()!=null) { 
            st = getState() + " ";
        }
        String add = "";
        if(getAddOn()!=null) {
            add = " " + getAddOn();
        }
        return getStreet() + " " + getHouseNumber() + add + ", " + getZipCode() + ", " + st + getCity() + ", " + getCountry();
    }
}
