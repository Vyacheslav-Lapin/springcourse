package lab.model;

public interface Person {
    String getName ();
    default void sayHello(Person person) {
        System.out.printf("Hello, %s!%n", person);
    }
}