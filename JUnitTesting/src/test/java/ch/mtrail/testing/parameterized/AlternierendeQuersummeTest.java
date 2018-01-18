package ch.mtrail.testing.parameterized;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AlternierendeQuersummeTest {

	@Parameters(name = "Summe für {0} : {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { //
				{ 0, 0 }, { 1, 1 }, //
				{ 21, 1 }, { 121, 0 } });
	}

	private long input;
	private int expected;
	private AlternierendeQuersumme alternierendeQuersumme;

	public AlternierendeQuersummeTest(long input, int expected) {
		this.input = input;
		this.expected = expected;
	}

	@Before
	public void setUp() throws Exception {
		this.alternierendeQuersumme = new AlternierendeQuersumme();
	}

	@Test
	public void test() {
		// given

		// when
		int res = alternierendeQuersumme.sum(input);

		// then
		assertEquals(expected, res);
	}

}
