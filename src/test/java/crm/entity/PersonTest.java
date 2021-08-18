package crm.entity;

import com.neovisionaries.i18n.CountryCode;
import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

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
    void shouldSavePersonInDatabase() {
        //given
        final var person = new Person("Jan", "Kowalski", new SsNumber("99101009878"));

        //when
        savaAndFlush(person);

        //then
        final var readPerson = session.get(Person.class, person.getId()); //take person class object by id from DB
        assertEquals(person, readPerson);

    }

    @Test
    void shouldAddAddress() {
        //given
        final var customer = new Person("Jan", "Kowalski", new SsNumber("99101009878"));
        final var address = new Address("stri", "Wwa", "11-222", CountryCode.PL);
        customer.addAddress(address);

        //when
//        session.save(address); //maunal saving address (not the best idea)
        savaAndFlush(customer);


        //then
        final var readCustomer = session.get(Customer.class, customer.getId());
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }


    private void savaAndFlush(Person person) {
        session.save(person); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();
    }

}