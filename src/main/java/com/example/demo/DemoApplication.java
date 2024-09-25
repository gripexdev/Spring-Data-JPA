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
                    21
            );

            Student maria2 = new Student(
                    "Maria",
                    "Jones",
                    "maria2.jones@gmail.com",
                    25
            );

            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmed.ali@gmail.com",
                    18
            );
            System.out.println("Adding maria and ahmed");
            studentRepository.saveAll(List.of(maria, maria2, ahmed));

            //Section-2
            studentRepository
                    .findStudentByEmail("ahmed.ali@gmail.com")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with email ahmed.ali@gmail.com not found"));

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeEquals(
                            "Maria",
                            21
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThan(
                            "Maria",
                            18
                    ).forEach(System.out::println);


        };
    }
}
