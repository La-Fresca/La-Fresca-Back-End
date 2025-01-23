package org.lafresca.lafrescabackend.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String FirstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String LastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String Email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String Password;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must contain only digits and an optional leading '+'")
    @Size(max = 15, message = "Phone number cannot exceed 15 digits")
    private String PhoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String Address;

    @NotBlank(message = "Role is required")
    private String Role;

    private String CafeId;

    private Integer Deleted = 0;

    private String username = Email;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "AVAILABLE|ON DELIVERY|ABSENT", message = "Status must be one of the following: AVAILABLE, ON DELIVERY, ABSENT")
    private String status; // AVAILABLE, ON DELIVERY, ABSENT

    private long statusUpdatedAt;

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        id = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + id +
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.Role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
