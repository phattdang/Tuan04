package fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.entities;

import fit.iuh.se.ltwww_java_11092025_tuan04_dangnguyentienphat.bai1.entities.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
//    @NotNull(message = "First name must be not null")
//    @NotEmpty(message = "lastName must be not empty")
//    @Size(min = 8, max = 50, message = "First name must be between 8 and 50 characters")
    private String firstName;

    @Column(name="last_name")
    @NotNull(message = "Last name must be not null")
    @NotEmpty(message = "Last name must be not empty")
    @Size(min = 8, max = 50, message = "Last name must be between 8 and 50 characters")
    private String lastName;
    @Column(name="email")
    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name="password")
    @NotEmpty(message = "Password must be not empty")
    private String password;

    @Column(name="dOB")
    @NotNull(message = "dOB must be not empty")
    private LocalDate dOB;
    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender must be not empty")
    private Gender gender;
    public User() {
    }

    public User(String firstName, String lastName, String email, String password, LocalDate dOB, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dOB = dOB;
        this.gender = gender;
    }

    public User(int id, String firstName, String lastName, String email, String password, LocalDate dOB, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dOB = dOB;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDate getdOB() {
        return dOB;
    }

    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
