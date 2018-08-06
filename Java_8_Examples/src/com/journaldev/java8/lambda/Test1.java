package com.journaldev.java8.lambda;

import com.journaldev.java8.defaultmethod.Interface1;

public class Test1 
{
	public static void main(String[] args) 
	{
		//classic way
		Runnable r = new Runnable(){
			@Override
			public void run() {
				System.out.println("My Runnable");
			}};
			
		//Lambda way
		Runnable r1 = () -> {
				System.out.println("My Runnable..Lambda");
			};
		
		Runnable r2 = () -> System.out.println("My Runnable..Lambda..level2");
		
		Interface1 i1 = (s) -> System.out.println(s);
		
		i1.method1("abc");
		
		
		
	}
	
}
