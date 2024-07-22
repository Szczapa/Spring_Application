package org.example.student.model;

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
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
