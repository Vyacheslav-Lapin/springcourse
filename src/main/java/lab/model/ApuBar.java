package lab.model;

import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

	public Squishee sellSquishee(Person person)  {
        if (person.isBroke())
            throw new CustomerBrokenException(
                    String.format("Person %s is broke!", person.getName()));

        System.out.println("Here is your Squishee");
        return new Squishee("Usual Squishee");
    }
}