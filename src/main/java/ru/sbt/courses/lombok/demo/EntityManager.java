package ru.sbt.courses.lombok.demo;

import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class EntityManager implements javax.persistence.EntityManager, AutoCloseable {

    @Delegate
    javax.persistence.EntityManager entityManager;

}
