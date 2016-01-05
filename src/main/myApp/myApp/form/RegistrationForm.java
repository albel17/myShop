package myApp.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

public class RegistrationForm {
    @NotNull
    @Size(min = 1, message = "Enter name.")
    private String name;

    @NotNull
    @Size(min = 1, message = "Enter surname.")
    private String surname;

    private Date birthdate;

    @NotNull
    @Size(min = 1, message = "Enter email.")
    @Pattern(regexp="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b",
            message = "Must be email.")
    private String email;

    @NotNull(message = "Enter password.")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters long.")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
