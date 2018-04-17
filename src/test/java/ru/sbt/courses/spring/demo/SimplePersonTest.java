package ru.sbt.courses.spring.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SimplePersonTest {

    BeanFactory context = new ClassPathXmlApplicationContext("ioc.xml");

    @Test
    @DisplayName("ioc works correctly")
    void iocSimpleTest() {
        assertThat(context.getBean("person"), is(
                getExpectedPerson()));
    }

    @NotNull
    private SimplePerson getExpectedPerson() {
        return new SimplePerson("John",
                "Smith",
                new SimpleCountry(1, "Russia", "RU"),
                17,
                1.78f,
                true,
                true,
                Collections.singletonList(
                        new SimpleContact("Telephone",
                                "+7(905)222-33-22")));
    }
}