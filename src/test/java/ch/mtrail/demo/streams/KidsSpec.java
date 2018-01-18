package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;

import static ch.mtrail.demo.streams.Kids.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Get names of all kids (under age of 18)
 */
public class KidsSpec {

    @Test
    public void getKidNameShouldReturnNamesOfAllKidsFromNorway() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        assertThat(getKidNames(collection), containsInAnyOrder("Sara", "Anna"));
        assertThat(getKidNames(collection), not(containsInAnyOrder("Viktor", "Eva")));
    }

}
