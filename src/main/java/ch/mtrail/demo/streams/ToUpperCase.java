package ch.mtrail.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class ToUpperCase {

	private ToUpperCase() {
	}

	public static List<String> transform(List<String> collection) {
		// List<String> newCollection = new ArrayList<>();
		// for (String element : collection) {
		// newCollection.add(element.toUpperCase());
		// }
		return collection.stream().map(String::toUpperCase).collect(Collectors.toList());
	}

}
