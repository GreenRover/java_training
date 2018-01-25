package ch.mtrail.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Joining {

	private Joining() {
	}

	public static String namesToString(List<Person> people) {
		return people.stream() //
				.map(Person::getName) //
				.collect(Collectors.joining(", ", "Names: ", "."));
	}
}
