package myApp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressForm {
    @NotNull
    @Size(min = 1, message = "Enter country.")
    private String country;

    @NotNull
    @Size(min = 1, message = "Enter city.")
    private String city;

    @NotNull
    @Size(min = 1, message = "Enter postal code.")
    private String postalCode;

    @NotNull
    @Size(min = 1, message = "Enter street.")
    private String street;

    @NotNull
    @Size(min = 1, message = "Enter house.")
    private String house;

    @NotNull
    @Size(min = 1, message = "Enter flat.")
    private String flat;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
