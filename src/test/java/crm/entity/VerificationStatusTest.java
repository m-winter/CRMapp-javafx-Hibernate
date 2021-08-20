package crm.entity;

import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VerificationStatusTest {

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
    void shouldVerifiedCustomer() {
        final var company = new Company("Biedra", new NipNumber("1234567890"));
        final var verification = new VerificationStatus(true, LocalDate.of(2021,8,19),"Maciej");

        company.addVerificationStatus(verification);

        savaAndFlush(company);

        final var readedCustomer = session.get(Customer.class, company.getId());
        System.out.println(readedCustomer.getVerificationStatus().toString());

        Assertions.assertEquals(company.getVerificationStatus().getId(),readedCustomer.getVerificationStatus().getId());

    }


    private void savaAndFlush(Company company) {
        session.save(company); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();
    }

}
