package ch.mtrail.demo.utils;

import java.util.Collection;

public class MinFinder<T extends Comparable<T>> {

	public T min(Collection<T> values) {
		if (values == null) {
			throw new IllegalArgumentException();
		}
		T minValue = null;
		for (T t : values) {
			if (minValue == null) {
				minValue = t;
			} else if (t == null) {
				continue;
			} else if (minValue.compareTo(t) > 0) {
				minValue = t;
			}

		}

		return minValue;
	}

}
