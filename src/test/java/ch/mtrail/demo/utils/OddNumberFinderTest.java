package ch.mtrail.demo.utils;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

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
			public void describeTo(Description arg0) {
				arg0.appendText(stringBuilder.toString());
			}

			@Override
			protected boolean matchesSafely(Collection<Integer> arg0) {
				boolean res = true;
				if (expectedValues.length != arg0.size()) {
					stringBuilder.append("wrong number of elements: expected is ").append(expectedValues.length)
							.append(" but was ").append(arg0.size());
					res = false;
				}

				return res;
			}
		};
	}

	@Test
	public void test() {
		// given
		Collection<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

		// when
		Collection<Integer> result = oddNumberFinder.odds(values);

		// then
		// assertThat(result.size(), is(3));
		// assertThat(result, hasItem(1));
		// assertThat(result, hasItem(3));
		// assertThat(result, hasItem(5));
		// assertThat(result, not(hasItem(4)));
		assertThat(result, all);
		assertThat(result, containsAllEntries(1, 3, 5));
	}

}
