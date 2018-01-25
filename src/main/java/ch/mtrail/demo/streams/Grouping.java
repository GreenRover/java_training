package ch.mtrail.demo.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping {

	private Grouping() {
	}

	public static Map<String, List<Person>> groupByNationality(List<Person> people) {
		return people.stream() //
				.collect(Collectors.groupingBy(Person::getNationality));
	}

}
