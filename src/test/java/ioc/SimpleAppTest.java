package ioc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static ioc.HelloWorldTest.getExpectedPerson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SimpleAppTest {

    @SuppressWarnings("WeakerAccess")
    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("ioc");

    @Test
	void testInitPerson() {
		assertThat(context.getBean("person"), is(getExpectedPerson()));
	}
}
