package org.lafresca.lafrescabackend.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ForgotPassword")
@Data
public class ForgotPassword {
    @Id
    private String id;

    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;

    @NotEmpty(message = "Random No cannot be empty")
    @NotNull(message = "Random No cannot be null")
    private Integer randomNo;
}
