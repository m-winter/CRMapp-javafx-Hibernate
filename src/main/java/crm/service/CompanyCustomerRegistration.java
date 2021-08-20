package crm.service;

import crm.entity.Company;
import crm.exception.CustomerAlreadyExistsException;
import crm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;

import static java.util.Objects.*;

public class CompanyCustomerRegistration {

    private final SessionFactory sessionFactory;


    public CompanyCustomerRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    public RegisteredCustomerId registeredCompany(RegisterCompanyForm form){
        final var session = sessionFactory.openSession();
        final var transaction = session.beginTransaction();


        if (isCompanyExists(form,session)){
            throw new CustomerAlreadyExistsException("Customer already exists! Check data");
        }
        final var company = Company.from(form);

        session.save(company);

        transaction.commit();

        session.close();

        return new RegisteredCustomerId(company.getId());

    }


    private Boolean isCompanyExists(RegisterCompanyForm form, Session session){
        return session.createQuery("select count(c) > 0 from Company c where c.companyName = ?1 and c.nipNumber.value = ?2", Boolean.class) //check if there is similar Person record in table return true if there is someone
                .setParameter(1, form.getCompanyName())
                .setParameter(2, form.getNip())
                .getSingleResult();
    }

}
