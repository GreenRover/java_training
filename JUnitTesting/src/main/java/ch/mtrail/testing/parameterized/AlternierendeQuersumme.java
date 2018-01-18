package ch.mtrail.testing.parameterized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlternierendeQuersumme {

	public int sum(long value) {
		List<Integer> digits = new ArrayList<>();
		long remainder = value;
		while (remainder > 0) {
			digits.add((int) (remainder % 10));
			remainder /= 10;
		}
		Collections.reverse(digits);
		return sum(digits, 1);
	}

	private int sum(List<Integer> digits, int factor) {
		if (digits.isEmpty()) {
			return 0;
		}
		return factor * digits.get(0) + sum(digits.subList(1, digits.size()), factor * -1);
	}

}
