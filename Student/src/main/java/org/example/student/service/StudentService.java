package org.example.student.service;

import org.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Integer, Student> students;

    public StudentService() {
        students = new HashMap<>();
        Student student = Student.builder()
                .studentId(1)
                .studentFirstName("John")
                .studentLastName("Doe")
                .studentEmail("john.Doe@Doe")
                .build();

        Student student2 = Student.builder()
                .studentId(2)
                .studentFirstName("Jane")
                .studentLastName("Doe")
                .studentEmail("jane.Doe@Doe")
                .build();

        Student student3 = Student.builder()
                .studentId(3)
                .studentFirstName("Jack")
                .studentLastName("Doe")
                .studentEmail("jack.Doe@Doe")
                .build();

        students.put(student.getStudentId(), student);
        students.put(student2.getStudentId(), student2);
        students.put(student3.getStudentId(), student3);

    }


    public Student getStudent(int id) {
        return students.values().stream()
                .filter(s -> s.getStudentId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getStudents(String Lastname) {
        if (Lastname.isEmpty()) {
            return List.copyOf(students.values());
        }
        return students.values().stream()
                .filter(s -> s.getStudentLastName().toLowerCase().contains(Lastname.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addStudent(Student student) {
        student.setStudentId(students.size() + 1);
        students.put(student.getStudentId(), student);
    }

    public void updateStudent(int id, Student student) {
        students.put(id, student);
    }

    public void deleteStudent(int id) {
        students.remove(id);
    }
}
