package crm.entity;

import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerContactListTest {
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
    void shouldAddListOfContactsToCustomer(){

        final var customer = new Company("Biedra", new NipNumber("1010101010"));

        final var email = new EmailContactItem("maciek@wp.pl");
        final var contactList = new ContactList();
        contactList.addContact(email);

        customer.addContactList(contactList);

        savaAndFlush(customer);

        final var read = session.get(EmailContactItem.class, customer.getContactList().getContacts().get(0).getId());
        Assertions.assertEquals(email,read);



    }


    private void savaAndFlush(Company company) {
        session.save(company); //adding person to "Map"
        session.flush(); // save "Map" to DB
        session.clear();
    }
}
