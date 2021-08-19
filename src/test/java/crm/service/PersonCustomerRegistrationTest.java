package crm.service;

import crm.util.HibernateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonCustomerRegistrationTest {

    private final PersonCustomerRegistration registration =
            new PersonCustomerRegistration(HibernateUtil.getSessionFactory());

    @Test
    void shouldRegisterPerson(){
        //given
        final var form = new RegisterPersonForm("maciek","kowalski","99020308831");

        //when
        final var registeredCustomerId = registration.registerPerson(form);

        //then
        assertNotNull(registeredCustomerId.getId());

    }



}