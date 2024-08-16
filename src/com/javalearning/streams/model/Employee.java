package com.javalearning.streams.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private long id;
	private String name;
	private double salary;

	public Employee() {
	}
	
	public Employee(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param salary
	 */
	public Employee(long id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

	public static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(101, "Malreddy", 100000));
		employees.add(new Employee(104, "Keerthi Kumar", 150000));
		employees.add(new Employee(109, "Nasira", 200000));
		employees.add(new Employee(103, "Kanil", 105000));
		employees.add(new Employee(105, "Anusha", 250000));
		employees.add(new Employee(101, null, 100000));
		return employees;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public static String upper(Employee emp) {
		return emp.getName().toUpperCase();
	}
	
}
