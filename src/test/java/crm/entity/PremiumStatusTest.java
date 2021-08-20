package crm.entity;

import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;

public class PremiumStatusTest {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session session;
    private Transaction transaction;

    @BeforeEach
    void before() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void after() {
        transaction.rollback(); // turn back transaction/ to avoid problems with DB
        session.close(); //close session
    }

    @Test
    void shouldAddPremiumStatus(){
        final var customer = new Person("maciek", "maciek", new SsNumber("12345678901"));
        final var premium = new PremiumStatus(true, LocalDate.of(2023, 10, 10), PremiumType.SILVER);

        customer.addPremiumStatus(premium);
        savaAndFlush(customer);

        final var readedCustomer = session.get(Person.class,customer.getId());

//        System.out.println(readedCustomer.getPremiumStatus().toString());

        Assertions.assertEquals(customer.getPremiumStatus().getPremiumType(),readedCustomer.getPremiumStatus().getPremiumType());

    }

    private void savaAndFlush(Person person) {
        session.save(person); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();
    }
}
