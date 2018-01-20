package ch.mtrail.demo.streams;

import org.junit.Test;

import java.util.List;

import static ch.mtrail.demo.streams.Joining.namesToString;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Return people names separated by comma
 */
public class JoiningTest {

    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        assertThat(namesToString(collection), is(equalTo("Names: Sara, Viktor, Eva.")));
    }

}
