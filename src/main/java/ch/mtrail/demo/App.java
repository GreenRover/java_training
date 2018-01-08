package ch.mtrail.demo;

import java.util.List;

import org.hibernate.Session;

import ch.mtrail.demo.model.Order;
import ch.mtrail.demo.model.Stock;
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
			// final Stock stock = new Stock();
			//
			// stock.setStockCode("4715");
			// stock.setStockName("GENM");
			//
			// session.save(stock);
			// System.out.println("Getting orders:");
			// List<Order> orders = session.createQuery("from Order").list();
			// for (Order order : orders) {
			// System.out.println("Order:" + order.getOrderId());
			// Stock stock = order.getStock();
			// System.out
			// .println("\t" + order.getAmount() + " * " + stock.getStockCode() + "-" +
			// stock.getStockName());
			// }

			System.out.println("Getting stocks:");
			List<Stock> stocks = session.createQuery("from Stock").list();
			for (Stock stock : stocks) {
				System.out.println("Stock: " + stock.getStockCode() + "-" + stock.getStockName());
				for (Order order : stock.getOrders()) {
					System.out.println("\t" + order.getOrderId() + " " + order.getAmount());
				}
			}
			session.getTransaction().commit();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}

	}
}
