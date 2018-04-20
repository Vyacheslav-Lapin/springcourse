package lab.jpa;

import lab.JavaConfig;
import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE)
@ContextConfiguration(classes = JavaConfig.class)
class CountryDaoImplTest {

    Country exampleCountry = new SimpleCountry("Australia", "AU");

    @Autowired
    CountryDao countryJpaDao;

    @Test
    void testSaveCountry() {

        countryJpaDao.save(exampleCountry);

        List<Country> countryList = countryJpaDao.getAllCountries();
        assertEquals(1, countryList.size());
        assertEquals(exampleCountry, countryList.get(0));
    }

    @Test
    void testGtAllCountries() {

        countryJpaDao.save(new SimpleCountry("Canada", "CA"));

        List<Country> countryList = countryJpaDao.getAllCountries();
        assertEquals(2, countryList.size());
    }

    @Test
    void testGetCountryByName() {
        assertThat(countryJpaDao.getCountryByName("Australia"), is(exampleCountry));
    }

}
