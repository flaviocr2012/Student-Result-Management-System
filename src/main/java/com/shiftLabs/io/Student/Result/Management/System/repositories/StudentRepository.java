package com.shiftLabs.io.Student.Result.Management.System.repositories;

import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentResponse, Long> {

}
