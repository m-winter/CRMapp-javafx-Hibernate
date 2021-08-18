package crm.entity;

import crm.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Test
    void shouldSavePersonInDatabase() {
        //given
        final var person = new Person("Jan", "Kowalski", new SsNumber("99101009878"));

        //when
        final var session = sessionFactory.openSession();
        final var transaction = session.beginTransaction();
        session.save(person); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();

        //then
        final var readPerson = session.get(Person.class, person.getId()); //take person class object by id from DB

        assertEquals(person, readPerson);
        transaction.rollback(); // turn back transaction/ to avoid problems with DB
    }
}