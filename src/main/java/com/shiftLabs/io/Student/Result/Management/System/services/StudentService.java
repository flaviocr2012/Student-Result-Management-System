package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.StudentNotFoundException;
import com.shiftLabs.io.Student.Result.Management.System.models.Result;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.STUDENT_MUST_BE_OLDER;
import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.STUDENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ResultRepository resulRepository;

    private final ModelMapper modelMapper;

    public StudentResponse registerStudent(StudentRequest studentRequest) {

        if (!isStudentOldEnough(studentRequest.getDateOfBirth())) {
            throw new IllegalArgumentException(STUDENT_MUST_BE_OLDER);
        }

        Student student = mapStudent(studentRequest);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponse.class);

    }

    public List<StudentResponse> retrieveStudentsList() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public void removeStudent(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentRepository.delete(student);
        } else throw new StudentNotFoundException(
                STUDENT_NOT_FOUND + studentId);
    }

    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(studentRequest.getFirstName());
            Student savedStudent = studentRepository.save(existingStudent);

            List<Result> resultsToUpdate = resulRepository.findByStudent(existingStudent);
            resultsToUpdate.forEach(result ->
                    result.setStudent(savedStudent));
            resulRepository.saveAll(resultsToUpdate);

            return modelMapper.map(savedStudent, StudentResponse.class);

        } else {
            throw new StudentNotFoundException(
                    STUDENT_NOT_FOUND + studentId);
        }

    }

    private boolean isStudentOldEnough(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= 10;
    }

    private Student mapStudent(StudentRequest studentRequest) {
        return modelMapper.map(studentRequest, Student.class);
    }

}


