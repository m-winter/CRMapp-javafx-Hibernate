package crm.service;

import crm.entity.Person;
import crm.exception.CustomerAlreadyExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static java.util.Objects.*;

public class PersonCustomerRegistration {
    private final SessionFactory factorySession;

    public PersonCustomerRegistration(SessionFactory factorySession) {
        this.factorySession = requireNonNull(factorySession);
    }

    public RegisteredCustomerId registerPerson(RegisterPersonForm form) {


        //open transaction for DB
        final var session = factorySession.openSession();
        final var transaction = session.beginTransaction();

        //validate ssnumber and name with HQL(Hibernate query language)

        if (getPersonExist(form, session)) {
            throw new CustomerAlreadyExistsException("Customer already exists! Check data" + form);
        }


        //change form into Person object
        final var person = Person.from(form);

        //save Person object to DB
        session.save(person);

        //return UUID(id) for saved user
        transaction.commit();
        session.close();
        return new RegisteredCustomerId(person.getId());
    }

    private Boolean getPersonExist(RegisterPersonForm form, Session session) {
        return session.createQuery("select count(p) > 0 from Person p where p.lastName = ?1 " +
                        "and p.ssNumber = ?2", Boolean.class) //check if there is similar Person record in table return true if there is someone
                .setParameter(1, form.getLastName())
                .setParameter(2, form.getSsNumber())
                .getSingleResult();
    }

}
