package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;

import static ch.mtrail.demo.streams.ToUpperCase.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Convert elements of a collection to upper case.
 */
public class ToUpperCaseTest {

    @Test
    public void transformShouldConvertCollectionElementsToUpperCase() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        assertThat(transform(collection), equalTo(expected));
    }

}
