package cn.linj2n.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private String province;

    private String country;

    @ManyToOne
    private Person person;

    protected Address(){
    }

    public Address(String street, String city, String province, String country) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("address {id=%d,info='%s','%s','%s','%s'}",id,street,city,province,country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(province, address.province) &&
                Objects.equals(country, address.country) &&
                Objects.equals(person, address.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, province, country, person);
    }
}
