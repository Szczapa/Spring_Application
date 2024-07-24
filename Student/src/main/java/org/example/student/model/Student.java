package org.example.student.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private int studentId;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name should contain only alphabets")
    private String studentFirstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name should contain only alphabets")
    private String studentLastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String studentEmail;
}
