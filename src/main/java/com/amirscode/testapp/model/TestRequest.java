package com.amirscode.testapp.model;

import com.amirscode.testapp.enums.Level;
import lombok.Data;
import lombok.NonNull;

@Data
public class TestRequest {

    private Level level;
    @NonNull
    private Long subjectId;
}
