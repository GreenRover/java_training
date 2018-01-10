package ch.mtrail.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import ch.mtrail.demo.model.Zugfahrt;
import ch.mtrail.demo.model.ZugfahrtSollpunkte;
import ch.mtrail.demo.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final List<Zugfahrt> fahrts = new ArrayList<>();
		try {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			final List<Zugfahrt> x = session.createQuery("select z from Zugfahrt z  join z.sollPunkte zs "
					+ "where z.zugId like '%-540-%' and zs.bp like 'DT' group by z.trainId ").list();
			fahrts.addAll(x );

			Instant start = Instant.now();
			Duration queryDuration = Duration.between(start, Instant.now());
			fahrts.stream().forEach(z -> {
				System.out.println("Fahrt: " + z);
				System.out.println("\t" + String.join(", ", z.getSollPunkte().stream().map(ZugfahrtSollpunkte::getBp).collect(Collectors.toSet())));
				
				z.setVersionNumber(43);
			});
			Duration fullDuration = Duration.between(start, Instant.now());
			System.out.println("Full duration: " + fullDuration.toMillis() + "ms Query duration: "
					+ queryDuration.toMillis() + "ms");

			// Nothing to commit / nothing changed.
			session.getTransaction().commit();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}
		
	}
}
