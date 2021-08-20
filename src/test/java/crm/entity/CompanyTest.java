package crm.entity;

import com.neovisionaries.i18n.CountryCode;
import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

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
    void shouldSaveToDB(){

         final var company = new Company("Sanik",new NipNumber("1234567890"));

         saveAndFlush(company);

         final var readedCompany = session.get(Company.class, company.getId());

        assertEquals(company,readedCompany);



    }
    @Test
    void shouldAddAddress(){
        final var company = new Company("Sanik",new NipNumber("1234567890"));
        final var address = new Address("stri", "Wwa", "11-222", CountryCode.PL);

        company.addAddress(address);
        saveAndFlush(company);

        final var readedCompany = session.get(Company.class, company.getId());
        assertEquals(company.getAddresses(),readedCompany.getAddresses());

    }

     private void saveAndFlush(Company company) {
         session.save(company); //adding person to "Map"
         session.flush(); // save "Map" to DB
         session.clear();
     }


}
