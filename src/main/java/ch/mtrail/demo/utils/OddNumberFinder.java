package ch.mtrail.demo.utils;

import java.util.Collection;
import java.util.stream.Collectors;

public class OddNumberFinder {
	public Collection<Integer> odds(Collection<Integer> values) {
		return values.stream().filter(v -> v % 2 != 0).collect(Collectors.toList());
	}
}
