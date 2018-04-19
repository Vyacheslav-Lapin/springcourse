package ru.sbt.courses.lombok.demo;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EntityManager implements javax.persistence.EntityManager, AutoCloseable {

    @Delegate
    javax.persistence.EntityManager entityManager;
}
