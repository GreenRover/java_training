package ch.mtrail.demo.streams;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.mtrail.demo.model.Zugfahrt;

public class FilterDuplicatedTrains {

	public static List<Zugfahrt> filter(final List<Zugfahrt> trains, final Date betriebstag) {
		return trains.stream() //
				.collect(Collectors.groupingBy(t -> t.getEvu() + "-" + t.getZugNr())).entrySet().stream() //
				// if we get more than one train in the group and at least one train operating
				// today, use only trains that operate today.
				.flatMap(e -> getTrainsOfBetriebsTagOrToday(e.getValue(), betriebstag)).collect(Collectors.toList());
	}

	private static Stream<Zugfahrt> getTrainsOfBetriebsTagOrToday(final List<Zugfahrt> trains, final Date betriebstag) {
		final Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		final List<Zugfahrt> trainsOfZukuenftigerVerkehrstag = trains.stream() //
				.filter(t -> t.getBetriebsTag().equals(betriebstag)) //
				.collect(Collectors.toList());

		if (trainsOfZukuenftigerVerkehrstag.size() > 0) {
			return trainsOfZukuenftigerVerkehrstag.stream();
		}

		final List<Zugfahrt> trainsOfToday = trains.stream() //
				.filter(t -> t.getBetriebsTag().equals(today.getTime())) //
				.collect(Collectors.toList());

		if (trainsOfToday.size() > 0) {
			return trainsOfToday.stream();
		}

		return trains.stream();
	}
}
