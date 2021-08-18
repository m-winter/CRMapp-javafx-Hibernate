package crm.service;

import crm.entity.Person;
import org.hibernate.SessionFactory;

import static java.util.Objects.*;

public class PersonCustomerRegistration {
    private final SessionFactory factorySession;

    public PersonCustomerRegistration(SessionFactory factorySession) {
        this.factorySession = requireNonNull(factorySession);
    }

    public RegisteredCustomerId registerPerson(RegisterPersonForm form){


        //open transaction for DB
        final var session = factorySession.openSession();
        final var transaction = session.beginTransaction();

        //validate ssnumber and name


        //change form into Person object
        final var person = Person.from(form);

        //save Person object to DB
        session.save(person);

        //return UUID(id) for saved user
        transaction.commit();
        session.close();
        return new RegisteredCustomerId(person.getId());
    }

}
