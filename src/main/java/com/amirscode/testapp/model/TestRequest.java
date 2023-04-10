package com.amirscode.testapp.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class TestRequest {

    private String level;
    @NonNull
    private Long subjectId;
}
