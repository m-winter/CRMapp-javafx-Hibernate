package crm.entity;

import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

public class ContactListTest {

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
    void shouldAddEmailToList() {

        final var email = new EmailContactItem("maciek@wp.pl");
        final var number = new PhoneContactItem("333333333");
        final var contactList = new ContactList();

        contactList.addContact(email);


        savaAndFlush(contactList);

        final var readedContact = session.get(EmailContactItem.class, contactList.getContacts().get(0).getId());


        Assertions.assertEquals(email, readedContact);

    }

    @Test
    void shouldAddPhoneToList() {
        final var number = new PhoneContactItem("333333333");
        final var contactList = new ContactList();

        contactList.addContact(number);

        savaAndFlush(contactList);

        final var readedContact = session.get(PhoneContactItem.class, contactList.getContacts().get(0).getId());
        Assertions.assertEquals(number, readedContact);

    }


    private void savaAndFlush(ContactList contactList) {
        session.save(contactList); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();
    }

}
