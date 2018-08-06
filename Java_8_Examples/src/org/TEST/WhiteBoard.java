package org.TEST;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class WhiteBoard 
{
	

	public static void main(String[] args) {
		
		List<Integer> numbers = new ArrayList<Integer>();
		
		numbers.add(11);numbers.add(37);numbers.add(52);
		
		Stream<Integer> oddNumbers = numbers.stream().filter(n -> n % 2 == 1);		
		
		oddNumbers.forEach(p->System.out.println("Odd -"+p));
		
		numbers.forEach(p->System.out.println("Num -"+p));
	
		//numbers = oddNumbers.mapToInt(mapper)
		
		List<Integer> intList = oddNumbers.collect(Collectors.toList());
		
		Integer[] intArray = oddNumbers.toArray(Integer[]::new);
		
	}
}
