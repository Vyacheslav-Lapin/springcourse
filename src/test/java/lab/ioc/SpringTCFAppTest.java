package lab.ioc;

import lab.JavaConfig;
import lab.model.Person;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lab.ioc.HelloWorldTest.getExpectedPerson;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@FieldDefaults(level = PRIVATE)
class SpringTCFAppTest {
	
	@Autowired
    Person person;

	@Test
	void testInitPerson() {
		assertThat(person, is(getExpectedPerson()));
	}
}
