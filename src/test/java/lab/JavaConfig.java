package lab;

import lab.model.Contact;
import lab.model.SimpleContact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

@Configuration
@ImportResource("classpath*:jpa.xml")
@ComponentScan({"lab.model", "lab.aop", "lab.dao"})
public class JavaConfig {

    @Bean
    List<Contact> contacts() {
        return Arrays.asList(
                new SimpleContact("TELEPHONE", "+7(905)222-33-22"),
                new SimpleContact("EMAIL", "dagf@mf.com"));
    }

//    @Bean
//    ServiceRegistry serviceRegistry() {
//        return new StandardServiceRegistryBuilder()
//                        .configure()
//                        .build();
//    }
//
//    @Bean
//    SessionFactory sessionFactory() {
//        return new MetadataSources(serviceRegistry())
//                        .addAnnotatedClass(SimpleCountry.class)
//                        .buildMetadata()
//                        .buildSessionFactory();
//    }
}
