package com.journaldev.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		
		System.out.println(parallelStreamMaxInteger(Arrays.asList(new Integer[]{})));
		
		System.out.println(parallelStreamMaxInteger(Arrays.asList(new Integer[]{1,5,20,7,9,15})));
		
		
/*		List<Integer> myList = new ArrayList<>();
		
		for(int i=0; i<100; i++) 
			myList.add(i);
		
		//sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		
		//parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();
		
		//using lambda with Stream API, filter example
		Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
		//using lambda in forEach
		highNums.forEach(p -> System.out.println("High Nums parallel="+p));
		
		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
		highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));

		
		
		//EXMP-2
		List<Person> personList = new ArrayList<Person>();
		List<String> names = new ArrayList<String>(); 
		
		names = personList.stream().
		filter(p->p.getGender()==Gender.FEMALE)
		.map(Person::getName).map(String::toUpperCase)
		//.forEach(name->names.add(name))
		.collect(Collectors.toList())
		;
		

		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));
		 
		List<String> phones = people.values().stream()
		  .flatMap(Collection::stream)
		    .collect(Collectors.toList());*/
		
	}
	
	//sort using java 8
	private void sortUsingJava8(List<String> names) {
		names = names.stream().sorted((s1,s2)->s1.compareTo(s2)).collect(Collectors.toList());
	}

	public static int  parallelStreamMaxInteger(List<Integer> integers)
	{
	   // return integers.parallelStream().reduce(Integer::max).get();
		
		return integers.stream().reduce(Integer.MIN_VALUE,(a,b)->Integer.max(a, b));
		//return integers.stream().reduce(Integer.MIN_VALUE,(a, b)-> Integer.max(a, b));
		
	}
	
	
	
	class Person { 
		   Gender gender; String name; 
		   public Gender getGender() { return gender; }
		   public String getName() { return name; }
		}
	
	enum Gender { MALE, FEMALE, OTHER }
		
}