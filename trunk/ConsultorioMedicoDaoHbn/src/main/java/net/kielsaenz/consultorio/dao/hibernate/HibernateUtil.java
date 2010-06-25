package net.kielsaenz.consultorio.dao.hibernate;

import java.io.File;
import java.net.URL;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.KeyValue;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.type.Type;

/**
 * Esta clase contiene los mÃ©todos de persistencia implementados
 * 
 * @author dew - Grupo 04
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
     *
     */
	public static void shutdown() {
		getSessionFactory().close();
	}

	public static void main(String arg[]) {
		Session hs = null;
		SessionFactory hsf = HibernateUtil.getSessionFactory();
		try {
			System.out.println("Obteniendo la sección actual");
			hs = hsf.getCurrentSession();
			System.out.println("Is Open: " + hs.isOpen());
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			hs.close();
		}
	}
}
