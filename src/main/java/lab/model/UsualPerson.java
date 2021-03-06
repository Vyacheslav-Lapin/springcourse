package lab.model;

import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import java.util.List;

@Value
@Component("person")
public class UsualPerson implements Person {
    long id;
    String name;

    Country country;

    int age;
    float height;
    boolean isProgrammer;

    @Wither
    boolean broke;

    List<Contact> contacts;
}
