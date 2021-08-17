package crm.util;

import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {

    @Test
    void testConnection() {
        //given
        final var sessionFactory = HibernateUtil.getSessionFactory();
        final var session = sessionFactory.openSession();

        //when
        final NativeQuery<Object> query = session.createSQLQuery("SELECT 1");
        final List<Object> resultList = query.getResultList();

        //then
        assertEquals(1, resultList.get(0));

    }
}