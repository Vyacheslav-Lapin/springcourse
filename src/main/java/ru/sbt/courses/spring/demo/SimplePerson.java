package ru.sbt.courses.spring.demo;

import lab.model.Contact;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Value
@Component("person")
public class SimplePerson implements Person {
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;
    boolean broke;
    List<Contact> contacts;
}
