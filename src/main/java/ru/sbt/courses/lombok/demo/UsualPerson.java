package ru.sbt.courses.lombok.demo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

//@Accessors(fluent = true)
//@Data //@Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
//@EqualsAndHashCode(exclude = "id")
//@AllArgsConstructor
//@NoArgsConstructor(force = true)

@Log4j2
@Builder // @AllArgsConstructor
@Value //@Getter @ToString @EqualsAndHashCode @AllArgsConstructor @FieldDefaults(level = PRIVATE, makeFinal = true)
@FieldDefaults(level = PRIVATE)
public class UsualPerson implements Person {

    //    @Setter(onMethod = @__(@Override))
    int id;
    String name;
    int age;
    @Builder.Default
    float height = 2.0f;
    @Wither
    boolean isProgrammer;
    @Singular
    List<String> contacts;
    @NonFinal
    Country country;

    @SneakyThrows
    public static void main(final String... args) {

        Class.forName("org.slf4j.MDC");

        val person = UsualPerson.builder()
                .id(5)
                .height(1.78f)
                .contacts(Arrays.asList("222-33-22", "sdf@lksjf.com"))
                .contact("222-33-22")
                .contact("sdf@lksjf.com")
                .build();

        try (EntityManager entityManager = null) {
            entityManager.merge(new Object());
        }

        UsualPerson usualPerson = person.withProgrammer(false);
    }

    public void sayHello(UsualPerson person) {
        int x, y = 0;

        //...
//        person.ge(54).setId(54);
    }
}