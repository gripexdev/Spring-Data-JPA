package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by email
    Optional<Student> findStudentByEmail(String email);

    // Find student by firstName and age
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(
            String firstName, Integer age);

    // Find student by first name and age greater than
    List<Student> findStudentsByFirstNameEqualsAndAgeGreaterThan(
            String firstName, Integer age);
}
