package lab.aop;

import lab.model.Person;
import lab.model.Squishee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Politeness {

    @Pointcut("@annotation(Polite)")
    private void pointcut() {
    }

    @Before("pointcut() && args(person)")
    public void sayHello(Person person) {
        System.out.printf("Hello %s. How are you doing?%n", person.getName());
    }

    @AfterReturning(pointcut = "pointcut()", returning = "retVal", argNames = "retVal")
    public void askOpinion(Squishee retVal) {
        System.out.printf("Is %s Good Enough?%n", retVal.getName());
    }

    @AfterThrowing("pointcut()")
    public void sayYouAreNotAllowed() {
        System.out.println("Hmmm...");
    }

    @After("pointcut()")
    public void sayGoodBye() {
        System.out.println("Good Bye!");
    }

    @Around("pointcut()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Hi!");
        Object retVal = pjp.proceed();
        System.out.println("See you!");
        return retVal;
    }

}