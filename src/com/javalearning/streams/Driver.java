package com.javalearning.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.javalearning.streams.model.Employee;
import com.javalearning.streams.model.EmployeeDto;

public class Driver {
	public static void main(String[] args) {
		
		
		List<Integer> intList1 = Arrays.asList(1,2,3);
		List<Integer> intList2 = Arrays.asList(4,5,6);
		List<Integer> intList3 = Arrays.asList(7,8,9);
		
		List<List<Integer>> listOfLists = Arrays.asList(intList1, intList2, intList3);
		
		for(List<Integer> list: listOfLists) {
			System.out.println(list);
		}
		
		Set<Integer> flattenedList = listOfLists.stream().flatMap(list->list.stream()).collect(Collectors.toSet());
		flattenedList.stream().forEach(System.out::println);
		
		List<List<String>> listOfListString = Arrays.asList(Arrays.asList("G", "E", "E"),
				Arrays.asList("K", "S", "F"),
				Arrays.asList("O", "R", "G"),
				Arrays.asList("E", "E", "K", "S"));
		
		Set<String> flattenedListString = listOfListString.stream().flatMap(list->list.stream()).collect(Collectors.toSet());
		System.out.println(flattenedListString);
		
		/*
		List<Employee> empList = Employee.getEmployees();
		
		List<EmployeeDto> empDtoList = empList.stream().map(emp->new EmployeeDto(emp.getId(), emp.getName(), emp.getSalary())).collect(Collectors.toList());
		empDtoList.stream().forEach(System.out::println);
		
		//eliminate duplicates
		System.out.println("Elominate Duplicates");
		Set<EmployeeDto> empSet = empList.stream().map(emp->new EmployeeDto(emp.getId(), emp.getName(), emp.getSalary())).collect(Collectors.toSet());
		empSet.stream().forEach(emp->System.out.println(emp));
		
		List<Integer> intList = Arrays.asList(1,2,3,4,4,4);
		
		// 1 2 3 4--> 1*1, 2*2, 3*3, 4*4
		List<Integer>  squaresList = intList.stream().map(i->i*i).collect(Collectors.toList());
		System.out.println(squaresList);
		
		Set<Integer> intSet = intList.stream().map(i->i*i).collect(Collectors.toSet());
		System.out.println(intSet);
		
		List<String> names = Arrays.asList("Malreddy", "Keerthi","Anusha","Meena");
		List<String> namesUppercase= names.stream().map(name->name.toUpperCase()).collect(Collectors.toList());
		System.out.println(namesUppercase);
		
		List<Integer> namesLength= names.stream().map(name->name.length()).collect(Collectors.toList());
		System.out.println(namesLength);
		
		
		List<Character> charsList = names.stream().map(name->name.charAt(1)).collect(Collectors.toList());
		System.out.println(charsList);
		
		
		List<String> names = Arrays.asList("Malreddy", "Keerthi","Anusha");
		names.forEach(name->System.out.println(name)); // foreach consumer
		names.forEach(System.out::println); // method refference
		
		
		Consumer<String> printUpperCase = (String s) -> System.out.println(s.toUpperCase());
		Consumer<String> printLowerCase = (String s) -> System.out.println(s.toLowerCase());

		printUpperCase.andThen(printLowerCase).accept("Hello, People");
		
		//HELLO PEOPLE hello people
		
		Consumer<String> infoLogger = (String message) -> System.out.println("INFO: " + message);
		Consumer<String> errorLogger = (String message) -> System.err.println("ERROR: " + message);

		infoLogger.accept("This is an info message.");
		errorLogger.accept("This is an error message.");
		
		
		   Optional<String> empty = Optional.empty();  
	       System.out.println(empty);  
	       
	       String name = "Malreddy";
	       
	      Optional<String> strOptinal =  Optional.ofNullable(name);
	      
	      System.out.println(strOptinal.isEmpty());
	       strOptinal.ifPresent(n->n.toUpperCase());
	      System.out.println(strOptinal.orElseThrow());
	      //System.out.println(strOptinal.get());
	      //System.out.println("Nullable Optional: "+Optional.ofNullable(null));  

		
		
		
		String names[] = new String[4];
		//names[3] ="Malreddy";

        Optional<String> checkNull = Optional.ofNullable(names[3]);  
        String defaultValue = checkNull.orElse("XYZ");
        System.out.println(defaultValue);
        if(checkNull.isPresent()) {
        	String lowercaseString = names[3].toLowerCase();  
            System.out.print(lowercaseString); 
        }

		List<Employee> empList = Employee.getEmployees();
		Employee firstEmployee = empList.stream().findFirst().get();
		System.out.println(firstEmployee); 
		Employee[] empArray = empList.stream()
								.toArray(Employee[]::new);
		for(int i=0;i<empArray.length;i++) {
			System.out.println(empArray[i]);
		}
		
		String str= "Amar";

		char ch = str.charAt(str.length()-1);
		if(str.contains(String.valueOf(ch))) {
			System.out.println(str+" ends with given character "+ch);
		}else {
			System.out.println(str+"doesn't ends with chaarcter "+ch);
		}
		
		
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
		
		*/
	}

}
