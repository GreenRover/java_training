package ch.mtrail.demo.streams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.mtrail.demo.model.Zugfahrt;

public class FilterDuplicatedTrains {

	public static List<Zugfahrt> filter(final List<Zugfahrt> trains, final Date betriebstag) {
		final Map<String, List<Zugfahrt>> trainsGrouped = new HashMap<>();
		for (Zugfahrt train : trains) {
			final String key = train.getEvu() + "-" + train.getZugNr();
			trainsGrouped.putIfAbsent(key, new ArrayList<>());
			
			trainsGrouped.get(key).add(train);
		}
		
		final List<Zugfahrt> result = new ArrayList<>();
		for (List<Zugfahrt> trainGroup : trainsGrouped.values()) {
			// if we get more than one train in the group and at least one train operating
			// today, use only trains that operate today.
			if (trainGroup.size() > 1) {
				result.addAll( getTrainsOfBetriebsTagOrToday(trainGroup, betriebstag) );
			} else {
				result.addAll( trainGroup );
			}
		}
		
		return result;
	}
	
	private static List<Zugfahrt> getTrainsOfBetriebsTagOrToday(final List<Zugfahrt> trains, final Date betriebstag) {
		final Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		final List<Zugfahrt> trainsOfZukuenftigerVerkehrstag = new ArrayList<>();
		for (Zugfahrt train : trains) {
			if (train.getBetriebsTag().equals(betriebstag)) {
				trainsOfZukuenftigerVerkehrstag.add(train);
			}
		}
		
		if (trainsOfZukuenftigerVerkehrstag.size() > 0) {
			return trainsOfZukuenftigerVerkehrstag;
		}
		
		final List<Zugfahrt> trainsOfToday = new ArrayList<>();
		for (Zugfahrt train : trains) {
			if (train.getBetriebsTag().equals(today.getTime())) {
				trainsOfToday.add(train);
			}
		}
		
		if (trainsOfToday.size() > 0) {
			return trainsOfToday;
		}
		
		return trains;
	}
}
