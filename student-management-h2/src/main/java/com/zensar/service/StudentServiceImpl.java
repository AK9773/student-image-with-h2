package com.zensar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.entity.Student;
import com.zensar.exception.EmptyStudentList;
import com.zensar.exception.StudentNotFound;
import com.zensar.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getAllStudent() {
		if( studentRepository.findAll().isEmpty()) {
			throw new EmptyStudentList();
		}
		return studentRepository.findAll();
	}

	public Student getStudent(int studentId) {
		if(!studentRepository.findById(studentId).isPresent()) {
			throw new StudentNotFound();
		}
		return studentRepository.findById(studentId).get();
	}

	public void insertStudent(Student student) {
		studentRepository.save(student);

	}

	public void updateStudent(int studentId, Student student) {
		studentRepository.save(student);

	}

	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);

	}

}
