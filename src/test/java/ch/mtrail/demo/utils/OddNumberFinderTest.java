package ch.mtrail.demo.utils;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

public class OddNumberFinderTest {

	private OddNumberFinder oddNumberFinder;

	@Before
	public void setUp() throws Exception {
		oddNumberFinder = new OddNumberFinder();
	}

	private static Matcher<Collection<Integer>> containsAllEntries(final Integer... expectedValues) {
		return new TypeSafeMatcher<Collection<Integer>>() {
			private StringBuilder stringBuilder = new StringBuilder();

			@Override
			public void describeTo(Description description) {
				description.appendText(stringBuilder.toString());
			}

			@Override
			protected boolean matchesSafely(Collection<Integer> actualValues) {
				boolean res = true;
				List<Integer> expectedValuesList = Arrays.asList(expectedValues);
				stringBuilder.append(expectedValuesList);
				if (expectedValues.length != actualValues.size()) {
					stringBuilder.append("\n\t").append("wrong number of elements: expected is ")
							.append(expectedValues.length).append(" but was ").append(actualValues.size());
					res = false;
				}
				if (!actualValues.containsAll(expectedValuesList)) {
					stringBuilder.append("\n\t").append("does not contain all elements ");
					res = false;
				}
				LinkedList<Integer> toEmpty = new LinkedList<>();
				toEmpty.addAll(expectedValuesList);
				actualValues.stream().forEach(i -> toEmpty.remove(i));
				if (toEmpty.size() != 0) {
					stringBuilder.append("\n\t").append("missing: " + toEmpty);
					res = false;
				}

				return res;
			}
		};
	}

	@Test
	public void odds_shouldContainAllOdds_whenRepeatingEntries() {
		// given
		Collection<Integer> values = Arrays.asList(1, 5, 7, 2, 3, 4, 5);

		// when
		Collection<Integer> result = oddNumberFinder.odds(values);

		// then
		assertThat(result, containsAllEntries(1, 3, 5, 5, 7));
		// assertThat(result, containsInAnyOrder(1, 3, 5, 7, 5));
	}

}
