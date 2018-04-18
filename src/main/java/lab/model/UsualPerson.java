package lab.model;

import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Value
@Component("person")
public class UsualPerson implements Person {
    int id;
    String name;

    Country country;

    int age;
    float height;
    boolean isProgrammer;

    List<Contact> contacts;
}
