package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public Employee findEmployeeByEmail(String email);

	public Employee findByAccountsAccountNumber(String accountNumber);

	public Employee findByFirstNameAndEmail(String firstName, String email);
}
