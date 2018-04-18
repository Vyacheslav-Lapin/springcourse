package lab.aop;

import lab.model.Person;
import lab.model.Squishee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Politeness {

    @Before("execution(* sellSquishee(..))")
    public void sayHello(JoinPoint joinPiont) {
        AopLog.append("Hello " + ((Person) joinPiont.getArgs()[0]).getName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        AopLog.append("Is " + ((Squishee) retVal).getName() + " Good Enough? \n");
    }

    public void sayYouAreNotAllowed() {
        AopLog.append("Hmmm... \n");
    }

    public void sayGoodBye() {
        AopLog.append("Good Bye! \n");
    }

    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        AopLog.append("Hi! \n");
        Object retVal = pjp.proceed();
        AopLog.append("See you! \n");
        return retVal;
    }

}