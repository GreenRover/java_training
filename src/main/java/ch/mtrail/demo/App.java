package ch.mtrail.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

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
		try {
			session.beginTransaction();

			Instant start = Instant.now();
			@SuppressWarnings("unchecked")
			List<Zugfahrt> fahrts = session.createQuery("select z from Zugfahrt z left join z.sollPunkte zs "
					+ "where z.zugId like '%-560-%' and zs.bp like 'DT' group by z.trainId ").list();
			System.out.println("Query duration: " + Duration.between(start, Instant.now()).toMillis() + "ms");
			fahrts.stream().forEach(z -> {
				System.out.println("Fahrt: " + fahrtToString(z));
				z.getSollPunkte().stream().forEach(App::printSollPunkt);
			});
			System.out.println("Full duration: " + Duration.between(start, Instant.now()).toMillis() + "ms");

			session.getTransaction().commit();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}

	}

	private static String fahrtToString(Zugfahrt fahrt) {
		StringBuilder sb = new StringBuilder(fahrt.getZugId());
		sb.append("/").append(fahrt.getEvu());
		sb.append("/").append(fahrt.getZugId());
		sb.append("/").append(fahrt.getBetriebsTag());
		sb.append("/").append(fahrt.getVersionNumber());
		sb.append("/").append(fahrt.getIsFakZugfahrt());
		sb.append("/").append(fahrt.getIsNationalTrain());
		sb.append("/").append(fahrt.getIsPassengerTrain());
		return sb.toString();
	}

	private static void printSollPunkt(ZugfahrtSollpunkte sollPunkt) {
		System.out.println("Sollpunkt: " + sollPunktToString(sollPunkt));
	}

	private static String sollPunktToString(ZugfahrtSollpunkte sollPunkt) {
		StringBuilder sb = new StringBuilder(sollPunkt.getBpId());
		sb.append("/").append(sollPunkt.getPosition());
		sb.append("/").append(sollPunkt.getBp());
		sb.append("/").append(sollPunkt.getZeitAn());
		sb.append("/").append(sollPunkt.getZeitAb());
		return sb.toString();
	}
}
