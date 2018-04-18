package lab.model;

import lab.aop.Polite;
import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

    @Polite
	public Squishee sellSquishee(Person person)  {
        if (person.isBroke())
            throw new CustomerBrokenException(
                    String.format("Person %s is broke!", person.getName()));

        System.out.println("Here is your Squishee");
        return new Squishee("Usual Squishee");
    }
}