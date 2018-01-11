package ch.mtrail.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ch.mtrail.demo.model.Zugfahrt;
import ch.mtrail.demo.persistence.CloseableSession;
import ch.mtrail.demo.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		final List<Zugfahrt> fahrts = new ArrayList<>();
		try (final CloseableSession closeableSession = new CloseableSession(
				HibernateUtil.getSessionFactory().openSession())) {
			final Session session = closeableSession.delegate();

			session.beginTransaction();
			Instant start = Instant.now();

			final Query<Zugfahrt> query = session.createQuery("SELECT z FROM Zugfahrt z " + //
					" LEFT JOIN z.zugfahrtSollpunkte sp " + //
					" LEFT JOIN sp.haltezwecke " + //
					" WHERE z.zugNr = :zugNr AND sp.bp = :bp" + //
					" GROUP BY z.trainId", Zugfahrt.class);
			query.setParameter("zugNr", 540);
			query.setParameter("bp", "DT");

			Duration queryDuration = Duration.between(start, Instant.now());

			fahrts.addAll(query.getResultList());

			fahrts.stream().forEach(z -> {
				System.out.println("Fahrt: " + z);
				System.out.println("\t" + String.join(", ", z.getZugfahrtSollpunkte().stream().map(bp -> {
					StringJoiner sj = new StringJoiner("-", " (", ")");
					sj.setEmptyValue("");
					bp.getHaltezwecke().stream().forEach(hz -> sj.add(hz.toString()));
					return bp.getBp() + sj.toString();
				}).collect(Collectors.toSet())));
			});
			Duration fullDuration = Duration.between(start, Instant.now());
			System.out.println("Full duration: " + fullDuration.toMillis() + "ms Query duration: "
					+ queryDuration.toMillis() + "ms");

			// Nothing to commit / nothing changed.
			session.getTransaction().rollback();
		}
	}
}
