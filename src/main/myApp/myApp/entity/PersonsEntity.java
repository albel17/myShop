package myApp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "persons", schema = "", catalog = "mydb")
public class PersonsEntity {
    private int id;
    private String name;
    private String surname;
    private Date birthdate;
    private String email;
    private String password;
    private int personType;
    private Collection<AddressesEntity> addressesById;
    private Collection<OrdersEntity> ordersesById;

    public PersonsEntity() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "BIRTHDATE")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "PERSON_TYPE")
    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonsEntity that = (PersonsEntity) o;

        return id == that.id && personType == that.personType && !(name != null ? !name.equals(that.name) : that.name != null) && !(surname != null ? !surname.equals(that.surname) : that.surname != null) && !(birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) && !(email != null ? !email.equals(that.email) : that.email != null) && !(password != null ? !password.equals(that.password) : that.password != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + personType;
        return result;
    }

    @OneToMany(mappedBy = "personsByClientId", fetch = FetchType.EAGER)
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "personsByClientId", fetch = FetchType.EAGER)
    public Collection<OrdersEntity> getOrdersesById() {
        return ordersesById;
    }

    public void setOrdersesById(Collection<OrdersEntity> ordersesById) {
        this.ordersesById = ordersesById;
    }

    public PersonsEntity(String name, String surname, Date birthdate, String email, String password, int personType) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "PersonsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", personType=" + personType +
                '}';
    }
}
