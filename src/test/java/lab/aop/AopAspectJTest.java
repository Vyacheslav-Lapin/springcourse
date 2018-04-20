package lab.aop;

import lab.JavaConfig;
import lab.TestUtil;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@FieldDefaults(level = PRIVATE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class AopAspectJTest {

    @Autowired
    Bar bar;

    @Autowired
    Person person;

    String squisheeAndOut;

    @BeforeEach
    void setUp() {
        squisheeAndOut = TestUtil.getSoutFrom(() ->
                bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enough...", squisheeAndOut.contains("Hello"));
        assertTrue("Before advice is not good enough...", squisheeAndOut.contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enough...", squisheeAndOut.contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken",
                squisheeAndOut.contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enough...",
                squisheeAndOut.contains("Hi!"));
        assertTrue("Around advice is not good enough...",
                squisheeAndOut.contains("See you!"));
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar); // "barObject instanceof ApuBar"
    }
}