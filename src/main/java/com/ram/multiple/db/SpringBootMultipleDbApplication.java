package com.ram.multiple.db;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.multiple.db.model.employee.Employee;
import com.ram.multiple.db.model.user.User;
import com.ram.multiple.db.reposotory.employee.EmployeeRepository;
import com.ram.multiple.db.reposotory.user.UserRepository;

@SpringBootApplication
@RestController
public class SpringBootMultipleDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDbApplication.class, args);
	}
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	public void addData2Db() {
		
		empRepo.saveAll(Stream.of(new Employee(1,"ram"),new Employee(2,"mohan"),new Employee(3,"reddy"),new Employee(4,"y")).collect(Collectors.toList()));
		
		userRepo.saveAll(Stream.of(new User(1,"mohan"),new User(2,"y"),new User(3,"ram"),new User(4,"reddy")).collect(Collectors.toList()));
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/employees")
	public List<Employee> getEmployess() {
		return empRepo.findAll();
	}
}
