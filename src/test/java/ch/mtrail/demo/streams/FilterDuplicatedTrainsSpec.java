package ch.mtrail.demo.streams;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.mtrail.demo.model.Zugfahrt;
import ch.mtrail.demo.persistence.CloseableSession;
import ch.mtrail.demo.persistence.HibernateUtil;

public class FilterDuplicatedTrainsSpec {
	
	private static List<Zugfahrt> trainsFromDb;

	private static List<Zugfahrt> getTrains(final int zugNr, final String bp) {
		
		try (final CloseableSession closeableSession = new CloseableSession(
				HibernateUtil.getSessionFactory().openSession())) {
			final Session session = closeableSession.delegate();

			session.beginTransaction();

			final Query<Zugfahrt> query = session.createQuery("SELECT z FROM Zugfahrt z " + //
					" LEFT JOIN z.zugfahrtSollpunkte sp " + //
					" LEFT JOIN sp.haltezwecke " + //
					" WHERE z.zugNr = :zugNr" + //
					(bp != null ? " AND sp.bp = :bp" : "") + //
					" GROUP BY z.trainId", Zugfahrt.class);
			query.setParameter("zugNr", zugNr);
			
			if (bp != null) {
				query.setParameter("bp", bp);				
			}
	
			return query.getResultList();
		}
	}
	
	@BeforeClass
	public static void getRainsFromDb() {
		trainsFromDb = getTrains(525, null);
	}

	
	@Test
	public void simpleAllDuplcated() {
		final Calendar cal09 = new GregorianCalendar(2018, 0, 9);

		final List<Zugfahrt> result = FilterDuplicatedTrains.filter(trainsFromDb, cal09.getTime());

        assertThat(result.stream().map(Zugfahrt::toString).collect(Collectors.toList()), containsInAnyOrder( //
        		"AAR-525-1 2018-01-09", //
        		"BD-525-1 2018-01-09", // 
        		"MGB-525-1 2018-01-09", //
        		"SBBP-525-1 2018-01-09", //
        		"TPC-525-1 2018-01-09" //
		));
	}
	
	@Test
	public void oneOfToday() {
		final SimpleDateFormat betriebsTagFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		
		final Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		
		final Calendar cal09 = new GregorianCalendar(2018, 0, 9);
		
		final Zugfahrt trainOfToday = new Zugfahrt("XXX", 111, 1, today.getTime(), 1, false, false, false); 

		final List<Zugfahrt> trains = new ArrayList<>(trainsFromDb);
		trains.add(trainOfToday);
		final List<Zugfahrt> result = FilterDuplicatedTrains.filter(trains, cal09.getTime());

        assertThat(result.stream().map(Zugfahrt::toString).collect(Collectors.toList()), containsInAnyOrder( //
        		"AAR-525-1 2018-01-09", //
        		"BD-525-1 2018-01-09", // 
        		"MGB-525-1 2018-01-09", //
        		"SBBP-525-1 2018-01-09", //
        		"TPC-525-1 2018-01-09", //
        		"XXX-111-1 " + betriebsTagFormat.format(today.getTime())
		));
	}
	
	@Test
	public void oneOfTodayAndYesterday() {
		final SimpleDateFormat betriebsTagFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		
		final Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		final Calendar tomorrow = GregorianCalendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 0);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		tomorrow.set(Calendar.MILLISECOND, 0);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		
		
		final Calendar cal09 = new GregorianCalendar(2018, 0, 9);
		
		final Zugfahrt trainOfToday = new Zugfahrt("XXX", 111, 1, today.getTime(), 1, false, false, false); 
		final Zugfahrt trainOfTomorrow = new Zugfahrt("XXX", 111, 1, tomorrow.getTime(), 1, false, false, false); 

		final List<Zugfahrt> trains = new ArrayList<>(trainsFromDb);
		trains.add(trainOfToday);
		trains.add(trainOfTomorrow);
		final List<Zugfahrt> result = FilterDuplicatedTrains.filter(trains, cal09.getTime());

        assertThat(result.stream().map(Zugfahrt::toString).collect(Collectors.toList()), containsInAnyOrder( //
        		"AAR-525-1 2018-01-09", //
        		"BD-525-1 2018-01-09", // 
        		"MGB-525-1 2018-01-09", //
        		"SBBP-525-1 2018-01-09", //
        		"TPC-525-1 2018-01-09", //
        		"XXX-111-1 " + betriebsTagFormat.format(today.getTime())
		));
	}
	
	@Test
	public void oneOfTodayDifferectVersions() {
		final SimpleDateFormat betriebsTagFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		
		final Calendar today = GregorianCalendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		
		final Calendar cal09 = new GregorianCalendar(2018, 0, 9);
		
		final Zugfahrt trainOfTodayA = new Zugfahrt("XXX", 111, 1, today.getTime(), 1, false, false, false); 
		final Zugfahrt trainOfTodayB = new Zugfahrt("XXX", 111, 2, today.getTime(), 1, false, false, false); 

		final List<Zugfahrt> trains = new ArrayList<>(trainsFromDb);
		trains.add(trainOfTodayA);
		trains.add(trainOfTodayB);
		final List<Zugfahrt> result = FilterDuplicatedTrains.filter(trains, cal09.getTime());

        assertThat(result.stream().map(Zugfahrt::toString).collect(Collectors.toList()), containsInAnyOrder( //
        		"AAR-525-1 2018-01-09", //
        		"BD-525-1 2018-01-09", // 
        		"MGB-525-1 2018-01-09", //
        		"SBBP-525-1 2018-01-09", //
        		"TPC-525-1 2018-01-09", //
        		"XXX-111-1 " + betriebsTagFormat.format(today.getTime()), //
				"XXX-111-2 " + betriebsTagFormat.format(today.getTime())
		));
	}
	
}
