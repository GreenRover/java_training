package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;

import static ch.mtrail.demo.streams.FlatCollection.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Flatten multidimensional collection
 */
public class FlatCollectionTest {

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        assertThat(transform(collection), containsInAnyOrder("Viktor", "Farcic", "John", "Doe", "Third"));
    }

}
