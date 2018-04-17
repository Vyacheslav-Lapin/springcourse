package lab.model;

import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
public class UsualPerson implements Person {
    int id;
    String name;

    Country country;

    int age;
    float height;
    boolean isProgrammer;

    List<String> contacts;

    public void sayHello(Person person) {
    }
}