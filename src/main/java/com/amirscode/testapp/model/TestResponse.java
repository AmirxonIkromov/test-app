package com.amirscode.testapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResponse {


    private String question;
    private String rightAnswer;
    private String option1;
    private String option2;
    private String option3;
}
