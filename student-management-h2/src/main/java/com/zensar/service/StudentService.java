package com.zensar.service;

import java.util.List;

import com.zensar.entity.Student;

public interface StudentService{
	
	public List<Student> getAllStudent();
	public Student getStudent(int studentId);
	public void insertStudent(Student student);
	public void updateStudent(int studentId, Student student);
	public void deleteStudent(int studentId);
	
}
