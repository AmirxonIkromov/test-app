package com.amirscode.testapp.model;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Builder
@Data
@Validated
public class RegisterRequest {

    private String fullName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
    @Size(min = 12, max = 60)
    private Integer age;
    @NonNull
    @Email
    private String email;
}
