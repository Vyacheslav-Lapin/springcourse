package ioc;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static ioc.HelloWorldTest.getExpectedPerson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleAppTest {
	
	static final String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

	private AbstractApplicationContext context;

    @BeforeEach
	void setUp() {
		context = new ClassPathXmlApplicationContext(
				APPLICATION_CONTEXT_XML_FILE_NAME);
	}

	@Test
	void testInitPerson() {
		assertThat(context.getBean("person"), is(getExpectedPerson()));
	}
}
