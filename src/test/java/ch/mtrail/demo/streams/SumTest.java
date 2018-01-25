package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;

import static ch.mtrail.demo.streams.Sum.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Sum all elements of a collection
 */
public class SumTest {

    @Test
    public void transformShouldConvertCollectionElementsToUpperCase() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertThat(calculate(numbers), is(equalTo(1 + 2 + 3 + 4 + 5)));
    }

}
