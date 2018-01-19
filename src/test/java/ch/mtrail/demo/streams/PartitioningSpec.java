package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static ch.mtrail.demo.streams.Partitioning.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Partition adults and kids
 */
public class PartitioningSpec {

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = partitionAdults(collection);
        assertThat(result.get(true), is(equalTo(asList(eva, viktor))));
        assertThat(result.get(false), is(equalTo(asList(sara))));
    }

}
