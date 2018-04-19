package ru.sbt.courses.lombok.demo;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

import java.io.Closeable;
import java.sql.Connection;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PooledConnection implements Connection {

    @Delegate(excludes = Closeable.class)
    Connection connection;

    @Override
    public void close() {
        //...
    }
}
