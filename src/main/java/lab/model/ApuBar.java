package lab.model;

import lab.aop.AopLog;
import org.springframework.stereotype.Component;

@Component
public class ApuBar implements Bar {

	public Squishee sellSquishee(Person person)  {
        if (person.isBroke())
            throw new CustomerBrokenException(
                    String.format("Person %s is broke!", person.getName()));

        AopLog.append("Here is your Squishee \n");
        return new Squishee("Usual Squishee");
    }
}