package com.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.entity.Employee;
import com.app.repo.EmployeeRepository;

import io.swagger.annotations.Api;

@Api(value = "EmployeeRestController", description = "REST Apis related to Employee Entity!!!!")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent())
			return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@GetMapping("employees")
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> list = employeeRepository.findAll();
		if (list.isEmpty())
			return new ResponseEntity<String>("Employee not Found", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@PostMapping("employees")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
		boolean flag = employeeRepository.save(employee) != null;
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/Employees/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("employees")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		employee = employeeRepository.save(employee);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Updated", HttpStatus.OK);
	}

	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id) {
		employeeRepository.deleteById(id);
		return new ResponseEntity<String>("Employee Delete by" + id + "perosn id", HttpStatus.NO_CONTENT);
	}

	@GetMapping("findByEmail/{email}")
	public ResponseEntity<?> getEmployeeByEmail(@PathVariable("email") String email) {
		Employee employee = employeeRepository.findEmployeeByEmail(email);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@GetMapping("findByAccountNumber/{accountNumber}")
	public ResponseEntity<?> findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		Employee employee = employeeRepository.findByAccountsAccountNumber(accountNumber);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@GetMapping("findAllEmployeeOrderByNameAsc")
	public ResponseEntity<?> findAllEmployeesOrderByNameAsc() {
		List<Employee> list = employeeRepository.findAll(Sort.by("firstName"));
		if (list.isEmpty())
			return new ResponseEntity<String>("Employee not Found", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@GetMapping("findAllEmployeeOrderByNameDesc")
	public ResponseEntity<?> findAllEmployeesOrderByNameDesc() {
		List<Employee> list = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
		if (list.isEmpty())
			return new ResponseEntity<String>("Employee not Found", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

	@GetMapping("findByNameAndEmail/{firstName}/{email}")
	public ResponseEntity<?> findByNameAndEmail(@PathVariable("firstName") String firstName,
			@PathVariable("email") String email) {
		Employee employee = employeeRepository.findByFirstNameAndEmail(firstName, email);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.OK);
	}

}
