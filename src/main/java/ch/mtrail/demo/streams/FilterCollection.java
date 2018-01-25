package ch.mtrail.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class FilterCollection {

	private FilterCollection() {
	}

	public static List<String> transform(List<String> collection) {
		return collection.stream() //
				.filter(e -> e.length() < 4) //
				.collect(Collectors.toList());
	}

}
