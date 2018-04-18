package ru.sbt.courses.spring.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("country")
@FieldDefaults(level = PRIVATE)
public class SimpleCountry implements Country {

    int id;

    String name;

    String codeName;

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}
