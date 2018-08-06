package org.TEST;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest 
{
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.forEach((t)->System.out.print(t+"->"));
		
		//list.forEach(Object::toString);
		System.out.println("\n****");
		
		list.clear();
		
		list.add(0,1);
		list.add(0,2);
		list.add(0,3);
		list.add(0,4);
		
		list.forEach((t)->System.out.print(t+"->"));
		System.out.println("\n****");
		
				
	}

}
