package ch.mtrail.demo.streams;

import static ch.mtrail.demo.streams.FilterCollection.transform;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

/*
Filter collection so that only elements with less then 4 characters are returned.
 */
public class FilterCollectionSpec {

    @Test
    public void transformShouldFilterCollection() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        assertThat(transform(collection), containsInAnyOrder("My", "is", "Doe"));
    }

}
