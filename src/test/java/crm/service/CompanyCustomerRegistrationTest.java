package crm.service;

import crm.util.HibernateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyCustomerRegistrationTest {

    private final CompanyCustomerRegistration registration =
            new CompanyCustomerRegistration(HibernateUtil.getSessionFactory());

    @Test
    void shouldRegisterCompany(){
        //given
        final var form = new RegisterCompanyForm("Biedra","1234567810");

        //when
        final var registeredCustomerId = registration.registeredCompany(form);

        //then
        assertNotNull(registeredCustomerId.getId());

    }
}
