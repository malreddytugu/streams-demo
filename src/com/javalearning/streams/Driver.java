package com.javalearning.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.javalearning.streams.model.Employee;

public class Driver {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		List<Employee> empList = Employee.getEmployees();

		// sort the employees by name
		empList.stream().filter(e -> e.getName() != null).sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
				.forEach(System.out::println);

		// last element in the list
		Employee lastEmployee = empList.stream().skip(empList.size() - 1).findFirst().get();
		System.out.println("last element in the emp list is :: " + lastEmployee);

		Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
		// infiniteStream.skip(2).limit(5).forEach(System.out::println); // 2 4 8 16 32
		// 64 128
		infiniteStream.limit(5).forEach(System.out::println);

		// int--primitive Integer--wrapper class--Object

		Stream<Integer> intgerStream = Stream.of(1, 2, 3, 4, 5, 6);
		intgerStream.forEach(System.out::println);

		System.out.println("Dealing with primitive data in Streams");
		IntStream intStream = IntStream.of(7, 8, 9, 10);
		// intStream.forEach(System.out::println);
		System.out.println(intStream.min().getAsInt());

		double max = DoubleStream.of(1.5, 2.7, 3.8, 4.9, 5.1).max().getAsDouble();
		System.out.println(max);

		double sum = DoubleStream.of(1.5, 2.7, 3.8, 4.9, 5.1).sum();
		System.out.println("sum : " + sum);

		double avg = DoubleStream.of(1.5, 2.7, 3.8, 4.9, 5.1).average().getAsDouble();
		System.out.println("Average : " + avg);

		Employee latest = empList.stream().min((e1, e2) -> e2.getId() - e1.getId())
				.orElseThrow(NoSuchElementException::new);

		System.out.println(latest);

		Employee maxSalEmp = empList.stream().max(Comparator.comparing(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);

		System.out.println("maxSalariedEmployee : " + maxSalEmp);

		// allMatch anyMatch noneMatch

		List<Integer> integerList = Arrays.asList(2, 4, 5, 6, 8, 10);

		System.out.println("is all numbers are even : " + integerList.stream().allMatch(n -> n % 2 == 0));
		System.out.println("is all numbers are even : " + integerList.stream().anyMatch(n -> n % 2 == 0));
		System.out.println("is all numbers are even : " + integerList.stream().noneMatch(n -> n % 2 == 0));

		System.out.println("IntStream.range(1,11)");
		IntStream.range(1, 11).forEach(System.out::println);
		System.out.println("IntStream.rangeClosed(1,10)");
		IntStream.rangeClosed(1, 10).forEach(System.out::println);

		IntStream finalIntStream = IntStream.concat(IntStream.of(1, 2, 3), IntStream.of(4, 5, 6));
		System.out.println("IntStream.concat");
		finalIntStream.forEach(System.out::println);

		// map returns Stream<Integer> not IntStream
		System.out.println("map returns Stream<Object>");
		Stream<Integer> integerStream = empList.stream().map(Employee::getId);
		integerStream.forEach(System.out::println);

		// mapToInt--->IntStream
		System.out.println("mapToInt");
		IntStream intStream1 = empList.stream().mapToInt(Employee::getId);
		intStream1.forEach(System.out::println);

		// reduction
		double sumSal = empList.stream().mapToDouble(Employee::getSalary).reduce(0.0, Double::sum);
		System.out.println(sumSal);
		
		
		String empNamesJoiningByComma = empList.stream().filter(e->e.getName()!=null).map(Employee::getName).collect(Collectors.joining(","));
		System.out.println(empNamesJoiningByComma);
		
		Vector<String> empVector = empList.stream().map(Employee::getName).collect(Collectors.toCollection(Vector::new));
		System.out.println(empVector); 
		
		//Non-thread safe ThreadSafe
		//ArrayList			Vector
		//HashMap           HashTable
		

		DoubleSummaryStatistics stats = empList.stream()
	      .collect(Collectors.summarizingDouble(Employee::getSalary));
		
		System.out.println("Min Salary : "+stats.getMin());
		System.out.println("Max salary :"+stats.getMax());
		System.out.println("Average : "+stats.getAverage());
		System.out.println("Sum : "+stats.getSum());
		System.out.println("Count : "+stats.getCount());
		
		
		IntSummaryStatistics intSummaryStatistics = IntStream.rangeClosed(1, 10).summaryStatistics();
		System.out.println(intSummaryStatistics);
		System.out.println(intSummaryStatistics.getMin());
		System.out.println(intSummaryStatistics.getMax());
		System.out.println(intSummaryStatistics.getAverage());
		System.out.println(intSummaryStatistics.getCount());
		System.out.println(intSummaryStatistics.getSum());
		
		IntSummaryStatistics integerSummary= Arrays.asList(1,2,3,4,5).stream().collect(Collectors.summarizingInt(e->e));
		System.out.println(integerSummary);
		
		/*
		String str ="welcome to Java Programming";
		
		for (int i = str.length()-1; i >0; i--) {
			System.out.print(str.charAt(i));
		}
		
		// emoclew ot avaJ gnimmargorP
		
		String[] words = str.split(" ");
		
		List<String> reversedStrList =Arrays.asList(words).stream().map(word->new StringBuffer(word).reverse().toString()).collect(Collectors.toList());
		System.out.println(reversedStrList);
		//eliminate duplicates
		List<Integer> intList = Arrays.asList(1,2,3,4,5,4,5,6,7,7,9,8);
		
		List<Integer> intListDistinct = intList.stream().distinct().collect(Collectors.toList());
		System.out.println(intListDistinct);
		
		List<String> names=Arrays.asList("Malreddy","Keerthi","Nasira","Amanbi","Ananth","Malreddy","Keerthi");
		List<String> uniqueNames=names.stream().distinct().collect(Collectors.toList());
		System.out.println(uniqueNames);
		
		List<String> namesSortedByLength = names.stream().distinct().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
		System.out.println(namesSortedByLength);
		
		//read a .txt file using java8
		
		Stream<String> linesStream;
		
		*/
		/*
		 * try { linesStream =
		 * Files.lines(Paths.get("D:\\Malreddy\\Training Institute\\git tutorial.txt"));
		 * linesStream.forEach(System.out::println); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		try {
			linesStream = Files.lines(Paths.get("D:\\Malreddy\\Training Institute\\git tutorial.txt"));
			linesStream.forEach(line->System.out.println(line));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//memory leaks will be handled by try with resources.
		
		try(Stream<String> lineStream= Files.lines(Paths.get("D:\\Malreddy\\Training Institute\\git tutorial.txt"))){
			lineStream.forEach(System.out::println);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
		/*
		List<String> empNames = Arrays.asList("Malreddy","Nasira",null,"Anusha","Amar","Meena");

		empNames.stream()
		.filter(Objects::nonNull)
		.map(e->e.toUpperCase()).forEach(System.out::println);
	
		
		//Method Refference Class::method
		// 1.Instance 2.constructor and 3.static
		List<Employee> empList = Employee.getEmployees();
		//print empIds
		//empList.stream().map(emp->emp.getId()).forEach(e->System.out.println(e));
		empList.stream().map(Employee::getId).forEach(System.out::println);
		
		
		//create List<Employee>
		//List<Employee> emps=empNames.stream().map(name->new Employee(name)).collect(Collectors.toList());
		List<Employee> emps=empNames.stream().map(Employee::new).collect(Collectors.toList());
		System.out.println(emps);
		
		//print uppercase of emp names
		//empList.stream().forEach(e->System.out.println(e.getName().toUpperCase()));
		List<String> empNamesUpper=empList.stream()
				.filter(e->e.getName()!= null)
				.map(Employee::upper).collect(Collectors.toList());
		System.out.println(empNamesUpper);
		
		
		//ParallelStream
		List<Integer> nums = Arrays.asList(101,304,605,507,786,951,1001,1189,207,302,156,99,86,11,9,6);
		List<Integer> numsGt300 =  nums.stream().sorted()
				.filter(n->n>300).collect(Collectors.toList());
		System.out.println(numsGt300);
		
		List<Integer> numsGt300Parallel =  nums.parallelStream().filter(n->n>300).collect(Collectors.toList());
		System.out.println(numsGt300Parallel);
		
		
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
		
		Set<String> flattenedListString = listOfListString.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());
		System.out.println(flattenedListString);
		
		List<Employee> empList = Employee.getEmployees();

		
		List<String> names= empList.stream().map(e->e.getName()).collect(Collectors.toList());
		List<String> namesUpper = names.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(namesUpper);
		
		
		
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
