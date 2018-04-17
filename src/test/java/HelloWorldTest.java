import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

    static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "application-context.xml";

    private BeanFactory context;

    @BeforeEach
    void setUp() {
        context = new FileSystemXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
    }

    @Test
    void testInitPerson() {
        assertEquals(getExpectedPerson(), context.getBean("person", Person.class));
    }

    private Person getExpectedPerson() {
        return new UsualPerson(1, "John Smith",
                new Country(1, "Russia", "RU"),
                35,
                1.78f,
                true,
                Arrays.asList("+7"));
    }
}
