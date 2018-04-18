package aop;

import io.vavr.Tuple2;
import ioc.JavaConfig;
import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import org.hamcrest.core.Is;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class AopAspectJExceptionTest {

    @Autowired
    Bar bar;

    @Autowired
    Person person;

    @BeforeEach
    void setUp() {
        person = person.withBroke(true);
    }

    @Test
    void testAfterThrowingAdvice() {
        Tuple2<CustomerBrokenException, String> out = TestUtil.getFromOut(() ->
                assertThrows(
                        CustomerBrokenException.class,
                        () -> bar.sellSquishee(person)));

        assertThat("Customer is not broken ", out._2, containsString("Hmmm...\n"));
    }
}