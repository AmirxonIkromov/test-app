package com.amirscode.testapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestRequest {

    private String level;
    @NonNull
    private Long subjectId;

    private String question;
    private String rightAnswer;
    private String option1;
    private String option2;
    private String option3;
}
