package lab.jdbc;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lab.JavaConfig;
import lab.dao.jdbc.JdbcCountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@FieldDefaults(level = PRIVATE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class JdbcTest {

    @Autowired
    JdbcCountryDao jdbcCountryDao;

    List<Country> expectedCountryList = new ArrayList<>();
    List<Country> expectedCountryListStartsWithA;
    Country countryWithChangedName = new SimpleCountry(1, "Russia", "RU");

    @BeforeEach
    void setUp() {
        initExpectedCountryLists();
        jdbcCountryDao.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = jdbcCountryDao.getAllCountries();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++)
            assertEquals(expectedCountryList.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = jdbcCountryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++)
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        jdbcCountryDao.updateCountryName("RU", "Russia");
        assertEquals(countryWithChangedName, jdbcCountryDao.getCountryByCodeName("RU"));
    }

//    @Benchmark
    private void initExpectedCountryLists() {
        List<Country> list = new ArrayList<>();
        int bound = JdbcCountryDao.COUNTRY_INIT_DATA.length;
        for (int i = 0; i < bound; i++) {
            Tuple2<Integer, String[]> intStringTuple = Tuple.of(i, JdbcCountryDao.COUNTRY_INIT_DATA[i]);
            SimpleCountry country = new SimpleCountry(
                    intStringTuple._1,
                    intStringTuple._2[0],
                    intStringTuple._2[1]);
            list.add(country);
        }
        expectedCountryList = list;

        List<Country> result = new ArrayList<>();
        for (Country simpleCountry : expectedCountryList) {
            if (simpleCountry.getName().startsWith("A")) {
                result.add(simpleCountry);
            }
        }
        expectedCountryListStartsWithA = result;
    }

//    @Benchmark
//    private void initExpectedCountryLists() {
//        expectedCountryList = IntStream.range(0, CountryDao.COUNTRY_INIT_DATA.length)
//                .mapToObj(i -> Tuple.of(i, CountryDao.COUNTRY_INIT_DATA[i]))
//                .map(intStringTuple -> new SimpleCountry(
//                        intStringTuple._1,
//                        intStringTuple._2[0],
//                        intStringTuple._2[1]))
//                .collect(Collectors.toList());
//
//        expectedCountryListStartsWithA = expectedCountryList.stream()
//                .filter(simpleCountry -> simpleCountry.getName().startsWith("A"))
//                .collect(Collectors.toList());
//    }

}