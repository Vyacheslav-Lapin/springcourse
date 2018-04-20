package lab.aop;

import lab.JavaConfig;
import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lab.TestUtil.getSoutFrom;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FieldDefaults(level = PRIVATE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AopAspectJExceptionTest {

    Bar bar;

    Person person;

    @BeforeEach
    void setUp() {
        person = person.withBroke(true);
    }

    @Test
    @DisplayName("Customer is broken")
    void testAfterThrowingAdvice() {
        assertThat(
                getSoutFrom(() -> assertThrows(
                        CustomerBrokenException.class,
                        () -> bar.sellSquishee(person))),
                containsString("Hmmm...\n"));
    }
}
