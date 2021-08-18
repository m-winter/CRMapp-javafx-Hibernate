package crm.util;

import crm.entity.Customer;
import crm.entity.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;


    static {//static constructor
        try {
            final var configuration = new Configuration();
            configuration.setProperties(loadHibernateProperties());
            configureEntities(configuration);
            sessionFactory = configuration.buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        }

    }

    private HibernateUtil(){}; //private constructor - avoid crating objects

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void configureEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Customer.class); //add entities
        configuration.addAnnotatedClass(Person.class);
    }

    private static Properties loadHibernateProperties() throws IOException {
        final var properties = new Properties();
        properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        return properties;
    }
}
