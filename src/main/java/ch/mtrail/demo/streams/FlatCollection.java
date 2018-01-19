package ch.mtrail.demo.streams;

import java.util.ArrayList;
import java.util.List;

public class FlatCollection {

    private FlatCollection() {
    }

    public static List<String> transform(List<List<String>> collection) {
        List<String> newCollection = new ArrayList<>();
        for (List<String> subCollection : collection) {
            for (String value : subCollection) {
                newCollection.add(value);
            }
        }
        return newCollection;
    }

}
