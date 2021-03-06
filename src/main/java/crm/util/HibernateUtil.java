package crm.util;

import crm.entity.*;
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
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(PremiumStatus.class);
        configuration.addAnnotatedClass(VerificationStatus.class);
        configuration.addAnnotatedClass(Contact.class);
        configuration.addAnnotatedClass(ContactList.class);
        configuration.addAnnotatedClass(EmailContactItem.class);
        configuration.addAnnotatedClass(PhoneContactItem.class);
    }

    private static Properties loadHibernateProperties() throws IOException {
        final var properties = new Properties();
        properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        return properties;
    }
}
