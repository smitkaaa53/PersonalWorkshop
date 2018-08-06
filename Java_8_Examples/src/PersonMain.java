
public class PersonMain {

	public static void main(String[] args) 
	{
		PersonFactory<Person> personFactory = Person::new;
		personFactory.create("abc", "bcd");

		PersonFactory<Person> personFactory1 = (a,b) -> new Person(a,b);
		
		
		System.out.println(personFactory.getClass().getName());
		System.out.println(personFactory1.getClass().getName());

		
	}

}
