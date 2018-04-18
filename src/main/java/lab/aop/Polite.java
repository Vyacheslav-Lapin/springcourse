package lab.aop;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link Politeness#pointcut()}
 */
@Retention(RUNTIME)
public @interface Polite {
}
