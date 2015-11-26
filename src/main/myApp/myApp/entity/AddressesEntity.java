package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Admin on 22.11.15.
 */
@Entity
@Table(name = "addresses", schema = "", catalog = "mydb")
public class AddressesEntity {
    private int id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String house;
    private String flat;
    private PersonsEntity personsByClientId;
    private Collection<OrdersEntity> ordersesById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "POSTAL_CODE")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "HOUSE")
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Basic
    @Column(name = "FLAT")
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressesEntity that = (AddressesEntity) o;

        if (id != that.id) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    public PersonsEntity getPersonsByClientId() {
        return personsByClientId;
    }

    public void setPersonsByClientId(PersonsEntity personsByClientId) {
        this.personsByClientId = personsByClientId;
    }

    @OneToMany(mappedBy = "addressesByAddressId")
    public Collection<OrdersEntity> getOrdersesById() {
        return ordersesById;
    }

    public void setOrdersesById(Collection<OrdersEntity> ordersesById) {
        this.ordersesById = ordersesById;
    }
}
