package com.amirscode.testapp.model;

import lombok.*;
import javax.validation.constraints.Size;


@Builder
@Data
public class RegisterRequest {

    private String fullName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
    @Size(min = 12, max = 60)
    private Integer age;
    @NonNull
//    @Email(message = "Email should be valid")
    private String email;
}
