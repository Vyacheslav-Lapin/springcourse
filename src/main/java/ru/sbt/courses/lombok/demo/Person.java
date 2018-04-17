package ru.sbt.courses.lombok.demo;

import java.util.List;

public interface Person {
    int getId();

//    Person setId(int id);

    String getName();

//    Person setName(String name);

    Country getCountry();

//    Person setCountry(Country country);

    int getAge();

//    Person setAge(int age);

    float getHeight();

//    Person setHeight(float height);

    boolean isProgrammer();

//    Person setProgrammer(boolean programmer);

    List<String> getContacts();

//    Person setContacts(List<String> contacts);
}
