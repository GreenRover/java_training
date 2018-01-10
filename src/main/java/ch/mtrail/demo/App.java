package ch.mtrail.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ch.mtrail.demo.model.Zugfahrt;
import ch.mtrail.demo.persistence.CloseableSession;
import ch.mtrail.demo.model.ZugfahrtSollpunkte;
import ch.mtrail.demo.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		final List<Zugfahrt> fahrts = new ArrayList<>();
		try (final CloseableSession closeableSession = new CloseableSession(HibernateUtil.getSessionFactory().openSession())) {
			final Session session = closeableSession.delegate();
			
			session.beginTransaction();

			final Query<Zugfahrt> query = session.createQuery("SELECT z FROM Zugfahrt z " + //
					" LEFT JOIN z.zugfahrtSollpunkte sp " + //
					" WHERE z.zugNr = :zugNr AND sp.bp = :bp" + //
					" GROUP BY z.trainId", Zugfahrt.class);
			query.setParameter("zugNr", 540);
			query.setParameter("bp", "DT");

			fahrts.addAll(query.getResultList());

			Instant start = Instant.now();
			Duration queryDuration = Duration.between(start, Instant.now());
			fahrts.stream().forEach(z -> {
				System.out.println("Fahrt: " + z);
				System.out.println("\t" + String.join(", ", z.getZugfahrtSollpunkte().stream().map(ZugfahrtSollpunkte::getBp).collect(Collectors.toSet())));
				
				z.setVersionNumber(43);
			});
			Duration fullDuration = Duration.between(start, Instant.now());
			System.out.println("Full duration: " + fullDuration.toMillis() + "ms Query duration: "
					+ queryDuration.toMillis() + "ms");

			// Nothing to commit / nothing changed.
			session.getTransaction().commit();
		}
		
	}
}
