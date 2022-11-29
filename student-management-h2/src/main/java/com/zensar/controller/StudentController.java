package com.zensar.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zensar.entity.ImageModel;
import com.zensar.entity.Student;
import com.zensar.service.StudentService;

@RestController
@RequestMapping(value = "/student-api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable("studentId") int studentId) {
		return studentService.getStudent(studentId);
	}

	@PostMapping(name = "/students", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public void createStudent(@RequestPart("student") Student student, @RequestPart("imageFile") MultipartFile[] file) {
		try {
			Set<ImageModel> images = uploadImage(file);
			student.setStudentImages(images);
			studentService.insertStudent(student);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels = new HashSet<ImageModel>();

		for (MultipartFile file : multipartFiles) {
			ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			imageModels.add(imageModel);
		}
		return imageModels;
	}

	@PutMapping("/students/{studentId}")
	public void updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
		studentService.updateStudent(studentId, student);
	}

	@DeleteMapping("/students/{studentId}")
	public void deleteStudent(@PathVariable("studentId") int studentId) {
		studentService.deleteStudent(studentId);
	}

}
