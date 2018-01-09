package ch.mtrail.demo;

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
		try (final CloseableSession closeableSession = new CloseableSession(HibernateUtil.getSessionFactory().openSession())) {
			final Session session = closeableSession.delegate();
			
			session.beginTransaction();

			final Query<Zugfahrt> query = session.createQuery("SELECT z FROM Zugfahrt z " + //
					" LEFT JOIN z.zugfahrtSollpunkte sp " + //
					" WHERE z.zugNr = :zugNr AND sp.bp = :bp" + //
					" GROUP BY z.trainId", Zugfahrt.class);
			query.setParameter("zugNr", 540);
			query.setParameter("bp", "DT");

			for (final Zugfahrt zugfahrt : query.getResultList()) {
				System.out.println(zugfahrt);
			}

			// Nothing to commit / nothing changed.
			session.getTransaction().rollback();
		}

	}
}
