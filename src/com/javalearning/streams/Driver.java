package com.javalearning.streams;

import java.util.List;
import java.util.stream.Collectors;

import com.javalearning.streams.model.Employee;

public class Driver {
	public static void main(String[] args) {
		
		/*
		String str= "Amar";

		char ch = str.charAt(str.length()-1);
		if(str.contains(String.valueOf(ch))) {
			System.out.println(str+" ends with given character "+ch);
		}else {
			System.out.println(str+"doesn't ends with chaarcter "+ch);
		}
		*/
		
		List<Employee> employees = Employee.getEmployees();
		//display all the employees in the List.
		employees.stream()
			.forEach(emp->System.out.println(emp));
		
		//print the employees whose name starts with "k"
		System.out.println("Employees whose Nme starts with 'K' ");
		for (Employee employee : employees) {
			if(employee.getName().contains("K")) {
				System.out.println(employee);
			}
		}
		
		//using java8 streams
		System.out.println("Employees whose Nme starts with 'K' using java8 streams ");
		List<Employee> employeesWithNamesStartsK = employees.stream()
			.filter(emp->emp.getName().contains("K"))
			.collect(Collectors.toList());
		employeesWithNamesStartsK.forEach(e->System.out.println(e));
		
	}

}
