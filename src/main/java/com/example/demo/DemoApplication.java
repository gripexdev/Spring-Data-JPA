package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "Jones",
                    "maria.jones@gmail.com",
                    22
            );
            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmed.ali@gmail.com",
                    18
            );
            //studentRepository.save(maria);
            System.out.println("Adding maria and ahmed");
            studentRepository.saveAll(List.of(maria, ahmed));

            System.out.print("Number of students:");
            System.out.println(studentRepository.count());

            // Find Student with ID 2 if not show error message
            studentRepository.findById(2L).ifPresentOrElse(student -> {
                System.out.println(student);
            }, () -> {
                System.out.println("Student with ID 2 not found");
            });

            // Find Student with ID 3 if not show error message
            studentRepository.findById(3L).ifPresentOrElse(student -> {
                System.out.println(student);
            }, () -> {
                System.out.println("Student with ID 3 not found");
            });

            // Find All Students
            System.out.println("Select all students");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            // Delete Student with id 1
            System.out.println("Delete maria");
            studentRepository.deleteById(1L);

            System.out.println("Number of students:");
            System.out.println(studentRepository.count());
        };
    }
}
