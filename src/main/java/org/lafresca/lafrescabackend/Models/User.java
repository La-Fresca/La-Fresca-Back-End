package org.lafresca.lafrescabackend.Models;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long UserId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String PhoneNumber;
    private String Address;
    private String Role;
    private Long CafeId;
    private Integer Deleted;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Long getCafeId() {
        return CafeId;
    }

    public void setCafeId(Long cafeId) {
        CafeId = cafeId;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String email, String password, String phoneNumber, String address, String role, Long cafeId, Integer deleted) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Address = address;
        Role = role;
        CafeId = cafeId;
        Deleted = deleted;
    }

    public User(Long userId) {
        UserId = userId;
    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public User(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Address='" + Address + '\'' +
                ", Role='" + Role + '\'' +
                ", CafeId=" + CafeId +
                '}';
    }
}

