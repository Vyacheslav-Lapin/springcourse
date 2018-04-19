package ioc;

import lab.model.Person;
import lab.model.SimpleContact;
import lab.model.SimpleCountry;
import lab.model.UsualPerson;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class HelloWorldTest {

    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("ioc");

    static Person getExpectedPerson() {
        return new UsualPerson(
                1,
                "John Smith",
                new SimpleCountry(1, "Russia", "RU"),
                35,
                1.78f,
                true,
                false,
                Arrays.asList(
                        new SimpleContact("TELEPHONE", "+7(905)222-33-22"),
                        new SimpleContact("EMAIL", "dagf@mf.com")));
    }

    @Test
    void testInitPerson() {
        assertThat(context.getBean("person", Person.class), is(getExpectedPerson()));
    }
}
