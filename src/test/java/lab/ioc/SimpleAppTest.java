package lab.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static lab.ioc.HelloWorldTest.getExpectedPerson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SimpleAppTest {

    @SuppressWarnings("WeakerAccess")
    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("lab");

    @Test
	void testInitPerson() {
		assertThat(context.getBean("person"), is(getExpectedPerson()));
	}
}
