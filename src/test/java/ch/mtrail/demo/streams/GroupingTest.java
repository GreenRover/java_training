package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static ch.mtrail.demo.streams.Grouping.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Group people by nationality
 */
public class GroupingTest {

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = groupByNationality(collection);
        assertThat(result.get("Norwegian"), containsInAnyOrder(sara, eva));
        assertThat(result.get("Serbian"), containsInAnyOrder(viktor));
    }

}
