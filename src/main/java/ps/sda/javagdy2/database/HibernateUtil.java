package ps.sda.javagdy2.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa - singleton.
 *
 * Jej odpowiedzialność to załadowanie konfiguracji hibernate z pliku konfiguracyjnego.
 * Po zaladowaniu konfiguracji tworzona jest fabryka sesji.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
