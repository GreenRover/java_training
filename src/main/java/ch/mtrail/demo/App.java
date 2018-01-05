package ch.mtrail.demo;

import org.hibernate.Session;

import ch.mtrail.demo.model.Stock;
import ch.mtrail.demo.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( final String[] args )
    {
    	System.out.println("Maven + Hibernate + MySQL");
    	final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            final Stock stock = new Stock();

            stock.setStockCode("4715");
            stock.setStockName("GENM");

            session.save(stock);
            session.getTransaction().commit();
        } finally {
        	session.close();
        	session.getSessionFactory().close();
		}
        
    }
}
