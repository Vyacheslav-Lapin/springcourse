package aop;

import io.vavr.Tuple2;
import ioc.JavaConfig;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Person;
import lab.model.Squishee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class AopAspectJTest {

    @Autowired
    Bar bar;

    @Autowired
    Person person;

    Tuple2<Squishee, String> squisheeAndOut;

    @BeforeEach
    void setUp() {
        squisheeAndOut = TestUtil.getFromOut(() -> bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enough...", squisheeAndOut._2.contains("Hello"));
        assertTrue("Before advice is not good enough...", squisheeAndOut._2.contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enough...", squisheeAndOut._2.contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken",
                squisheeAndOut._2.contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enough...",
                squisheeAndOut._2.contains("Hi!"));
        assertTrue("Around advice is not good enough...",
                squisheeAndOut._2.contains("See you!"));
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar); // "barObject instanceof ApuBar"
    }
}