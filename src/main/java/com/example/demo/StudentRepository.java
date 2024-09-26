package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by email (Using Method Name)
    Optional<Student> findStudentByEmail(String email);

    // Find student by email (Using JPQL)
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmailV2(String email);

    // Find student by firstName and age (Using Method Name)
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(
            String firstName, Integer age);

    // Find student by first name and age greater than equal (Using Method Name)
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(
            String firstName, Integer age);

    // Find student by first name and age greater than equal (Using JPQL)
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualV2(
            String firstName, Integer age);

    // Find student by first name and age greater than equal (Using Native Query)
    @Query(
            value = "SELECT * FROM Student WHERE first_name = ?1 AND age >= ?2",
            nativeQuery = true )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(
            String firstName, Integer age);

    // Find student by first name and age greater than equal (Using Native Query) with @Param
    @Query(
            value = "SELECT * FROM Student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNativeV2(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    // Delete Student by id, if deleted return = 1 // if not = 0
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = ?1")
    int deleteStudentById(Long id);
}
