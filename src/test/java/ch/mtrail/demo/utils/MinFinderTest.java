package ch.mtrail.demo.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MinFinderTest {

	private MinFinder<Integer> minFinder;

	@Before
	public void setup() {
		minFinder = new MinFinder();
	}

	@Test
	public void min_shouldThrowException_whenParamNullOldJUnit() {
		// given
		Collection<Integer> param = null;

		// when
		try {
			Integer min = minFinder.min(param);
			fail();
		} catch (IllegalArgumentException iae) {

		}

		// then

	}

	@Test(expected = IllegalArgumentException.class)
	public void min_shouldThrowException_whenParamNull() {
		// given
		Collection<Integer> param = null;

		// when
		Integer min = minFinder.min(param);

		// then

	}

	@Test
	public void min_shouldReturnNull_whenParamEmpty() {
		// given
		Collection<Integer> param = new ArrayList<>();

		// when
		Integer min = minFinder.min(param);

		// then
		assertNull(min);
	}

	@Test
	public void min_shouldReturnValue_whenParamOneValue() {
		// given
		Integer value = 5;
		Collection<Integer> param = Arrays.asList(value);

		// when
		Integer min = minFinder.min(param);

		// then
		assertEquals(value, min);
	}

	@Test
	public void min_shouldReturnValue_whenParamTwoValues() {
		// given
		Integer value1 = 5;
		Integer value2 = 3;
		Collection<Integer> param = Arrays.asList(value1, value2);

		// when
		Integer min = minFinder.min(param);

		// then
		assertEquals(value2, min);
	}

	@Test
	public void min_shouldIgnoreNull_whenParamContainsNull() {
		// given
		Integer value1 = 5;
		Integer value2 = null;
		Collection<Integer> param = Arrays.asList(value1, value2);

		// when
		Integer min = minFinder.min(param);

		// then
		assertEquals(value1, min);
	}

	@Test
	public void min_shouldReturnFirst_whenParamContainsEqualValues() {
		// given
		MinFinder<Date> dateMinFinder = new MinFinder<Date>();
		Date value1 = new Date(234234);
		Date value2 = new Date(234234);
		Collection<Date> param = Arrays.asList(value1, value2);

		// when
		Date min = dateMinFinder.min(param);

		// then
		assertSame(value1, min);
	}

}