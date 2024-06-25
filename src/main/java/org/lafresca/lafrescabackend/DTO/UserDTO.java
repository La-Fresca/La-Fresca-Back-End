package org.lafresca.lafrescabackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;
    private String Address;
    private String Role;
    private Long CafeId;
}
