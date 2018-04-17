package ru.sbt.courses.lombok.demo;

import ru.sbt.courses.spring.demo.Country;

import java.util.List;

public interface Person {
    int getId();

//    Person setId(int id);

    String getName();

//    Person setName(String name);

    Country getSimpleCountry();

//    Person setCountry(SimpleCountry country);

    int getAge();

//    Person setAge(int age);

    float getHeight();

//    Person setHeight(float height);

    boolean isProgrammer();

//    Person setProgrammer(boolean programmer);

    List<String> getContacts();

//    Person setContacts(List<String> contacts);
}
