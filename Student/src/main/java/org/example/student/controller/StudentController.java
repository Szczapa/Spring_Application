package org.example.student.controller;

import org.example.student.model.Student;
import org.example.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    private StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/students")
    public String students(@RequestParam(value = "Lastname", required = false, defaultValue = "") String Lastname, Model model) {
        model.addAttribute("students", studentService.getStudents(Lastname));
        return "students";
    }

    @GetMapping("/student/{id}")
    public String student(@PathVariable("id") int StudentId, Model model) {
        model.addAttribute("student", studentService.getStudent(StudentId));
        return "student";
    }


    @GetMapping("/student/add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student_form";
    }

    @PostMapping("/student/add")
    public String submitStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";

    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") int StudentId, Model model) {
        model.addAttribute("student", studentService.getStudent(StudentId));
        return "student_form";
    }

    @PostMapping("/student/edit/{id}")
    public String updateStudent(@PathVariable("id") int StudentId, @ModelAttribute("student") Student student) {
        studentService.updateStudent(StudentId, student);
        return "redirect:/students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") int StudentId) {
        studentService.deleteStudent(StudentId);
        return "redirect:/students";
    }
}
