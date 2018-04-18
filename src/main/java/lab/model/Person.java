package lab.model;

import java.util.List;

public interface Person {
    int getId();

    String getName();

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    List<String> getContacts();

    default void sayHello(Person person) {
        System.out.printf("Hello, %s!%n", person);
    }
}