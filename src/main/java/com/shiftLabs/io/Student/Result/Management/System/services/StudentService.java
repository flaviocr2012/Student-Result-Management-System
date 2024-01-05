package com.shiftLabs.io.Student.Result.Management.System.services;

import com.shiftLabs.io.Student.Result.Management.System.dtos.requests.StudentRequest;
import com.shiftLabs.io.Student.Result.Management.System.dtos.responses.StudentResponse;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.InvalidAgeException;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.InvalidDateFormatException;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.InvalidEmailException;
import com.shiftLabs.io.Student.Result.Management.System.exceptions.StudentNotFoundException;
import com.shiftLabs.io.Student.Result.Management.System.helpers.EmailValidator;
import com.shiftLabs.io.Student.Result.Management.System.helpers.StudentHelper;
import com.shiftLabs.io.Student.Result.Management.System.models.Result;
import com.shiftLabs.io.Student.Result.Management.System.models.Student;
import com.shiftLabs.io.Student.Result.Management.System.repositories.ResultRepository;
import com.shiftLabs.io.Student.Result.Management.System.repositories.StudentRepository;
import com.shiftLabs.io.Student.Result.Management.System.validator.DateOfBirthValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.shiftLabs.io.Student.Result.Management.System.constants.ExceptionConstant.*;

@Service
@Validated
public class StudentService {


    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;
    private final StudentHelper studentHelper;
    private final DateOfBirthValidator dateOfBirthValidator;

    @Autowired
    public StudentService(StudentRepository studentRepository, ResultRepository resultRepository, ModelMapper modelMapper, StudentHelper studentHelper, DateOfBirthValidator dateOfBirthValidator) {
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
        this.modelMapper = modelMapper;
        this.studentHelper = studentHelper;
        this.dateOfBirthValidator = dateOfBirthValidator;
    }

    public StudentResponse registerStudent(StudentRequest studentRequest) {

        if (!studentHelper.isStudentOldEnough(studentRequest.getDateOfBirth())) {
            throw new InvalidAgeException(STUDENT_MUST_BE_OLDER);
        }

        if (!EmailValidator.isValidEmail(studentRequest.getEmailAddress())) {
            throw new InvalidEmailException(EMAIL_MUST_BE_VALID);
        }

        dateOfBirthValidator.validateDateOfBirth(studentRequest.getDateOfBirth());

        Student student = studentHelper.mapStudent(studentRequest);
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

            List<Result> resultsToDelete = resultRepository.findByStudent(student);
            resultRepository.deleteAll(resultsToDelete);


        } else {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND + studentId);
        }
    }


    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(studentRequest.getFirstName());
            Student savedStudent = studentRepository.save(existingStudent);

            List<Result> resultsToUpdate = resultRepository.findByStudent(existingStudent);
            resultsToUpdate.forEach(result ->
                    result.setStudent(savedStudent));
            resultRepository.saveAll(resultsToUpdate);

            return modelMapper.map(savedStudent, StudentResponse.class);

        } else {
            throw new StudentNotFoundException(
                    STUDENT_NOT_FOUND + studentId);
        }

    }


}


