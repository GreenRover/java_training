package ch.mtrail.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class FlatCollection {

	private FlatCollection() {
	}

	public static List<String> transform(List<List<String>> collection) {
		return collection.stream() //
				.flatMap(l -> l.stream()) //
				.collect(Collectors.toList());
	}

}
