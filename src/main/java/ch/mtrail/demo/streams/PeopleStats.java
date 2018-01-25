package ch.mtrail.demo.streams;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class PeopleStats {

	private PeopleStats() {
	}

	public static Stats getStats(List<Person> people) {
		IntSummaryStatistics stat = people.stream() //
				.collect(Collectors.summarizingInt(p -> p.getAge()));
		return new Stats(people.size(), stat.getSum(), stat.getMin(), stat.getMax());
	}

}
