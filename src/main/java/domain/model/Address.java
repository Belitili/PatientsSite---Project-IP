package domain.model;

import domain.DomainException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author Marie
 */
@Entity
@Table(name="address")
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "address_seq_gen")
    @SequenceGenerator(name="address_seq_gen", sequenceName="ADDRESS_SEQ")
    private Long id; 
     
    @Column(name = "street", nullable = false)
    @NotNull
    @Size(min=1, max=50)
    private String street;
     
    @Column(name = "houseNumber", nullable = false)
    @NotNull
    @Min(1)
    private int houseNumber;
    
    @Column(name = "addOn", nullable = true)
    @NotNull
    @Size(min=1, max=30)
    private String addOn;
     
    @Column(name = "zipCode", nullable = false)
    @NotNull
    @Size(min=1, max=10)
    private String zipCode;
     
    @Column(name = "city", nullable = false)
    @NotNull
    @Size(min=1, max=20)
    private String city;
     
    @Column(name = "state", nullable = true)
    @NotNull
    @Size(min=1, max=20)
    private String state;
    
    @Column(name = "country", nullable = false)
    @NotNull
    @Size(min=1, max=20)
    private String country;

    public Address(String street, int houseNumber, String zipCode, String city, String country) {
        this.setValues(street, houseNumber, null, zipCode, city, null, country);
    }

    public Address(String street, int houseNumber, String addOn, String zipCode, String city, String state, String country) {
        this.setValues(street, houseNumber, addOn, zipCode, city, state, country);
    }
    
    public Address() {}
    
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
        DomainException.checkNotNull(id, "ID");
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        DomainException.checkNotNull(city, "City");
        DomainException.checkStringNotEmpty(city, "City");
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
        DomainException.checkNotNull(country, "Country");
        DomainException.checkStringNotEmpty(country, "Country");
        this.country = country;
    }
 
    public String getZipCode() {
        return zipCode;
    }
 
    public void setZipCode(String zipCode) {
        DomainException.checkNotNull(zipCode, "Zipcode");
        DomainException.checkStringNotEmpty(zipCode, "Zipcode");
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
        DomainException.checkNotNull(houseNumber, "Housenumber");
        DomainException.checkStringNotEmpty(String.valueOf(houseNumber), "Housenumber");
        DomainException.checkIfNumberAndPositive(String.valueOf(houseNumber), "Housenumber");
        this.houseNumber = houseNumber;
    }
 
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        DomainException.checkNotNull(street, "Street");
        DomainException.checkStringNotEmpty(street, "Street");
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
