package ioc;

import lab.model.Contact;
import lab.model.Country;
import lab.model.SimpleContact;
import lab.model.SimpleCountry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

@Configuration
@ImportResource("classpath*:jdbc.xml")
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
