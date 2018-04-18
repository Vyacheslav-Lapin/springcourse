import ioc.JavaConfig;
import lab.aop.AopLog;
import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    void setUp() throws Exception {
//        person.setBroke(true);
    }

    @Test
    void testAfterThrowingAdvice() {
        assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(person));

        assertTrue("Customer is not broken ",
                AopLog.getStringValue().contains("Hmmm..."));

        System.out.println(AopLog.getStringValue());
    }
}