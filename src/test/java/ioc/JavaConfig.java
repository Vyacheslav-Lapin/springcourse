package ioc;

import lab.model.Contact;
import lab.model.SimpleContact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

@Configuration
@ImportResource("classpath*:ioc-di.xml")
@ComponentScan("lab.model")
public class JavaConfig {

    @Bean
    List<Contact> contacts() {
        return Arrays.asList(
                new SimpleContact("TELEPHONE", "+7(905)222-33-22"),
                new SimpleContact("EMAIL", "dagf@mf.com"));
    }
}
