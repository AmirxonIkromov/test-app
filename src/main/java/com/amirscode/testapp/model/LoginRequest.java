package com.amirscode.testapp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

@Builder
@Data
public class LoginRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;
}
