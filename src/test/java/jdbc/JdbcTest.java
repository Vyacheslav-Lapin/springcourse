package jdbc;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import ioc.JavaConfig;
import lab.dao.CountryDao;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@FieldDefaults(level = PRIVATE)
class JdbcTest {

    @Autowired
    CountryDao countryDao;

    List<Country> expectedCountryList = new ArrayList<>();
    List<Country> expectedCountryListStartsWithA;
    Country countryWithChangedName = new SimpleCountry(1, "Russia", "RU");

    @BeforeEach
    void setUp() {
        expectedCountryListStartsWithA = initExpectedCountryLists();
        countryDao.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++)
            assertEquals(expectedCountryList.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++)
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        countryDao.updateCountryName("RU", "Russia");
        assertEquals(countryWithChangedName, countryDao.getCountryByCodeName("RU"));
    }

    private List<Country> initExpectedCountryLists() {
        List<Country> list = new ArrayList<>();
        List<Country> countries = expectedCountryList;
        int bound = CountryDao.COUNTRY_INIT_DATA.length;
        for (int i = 0; i < bound; i++) {
            Tuple2<Integer, String[]> intStringTuple = Tuple.of(i, CountryDao.COUNTRY_INIT_DATA[i]);
            SimpleCountry simpleCountry = new SimpleCountry(
                    intStringTuple._1,
                    intStringTuple._2[0],
                    intStringTuple._2[1]);
            countries.add(simpleCountry);
            if (simpleCountry.getName().startsWith("A")) {
                list.add(simpleCountry);
            }
        }
        return list;
    }

//    private List<Country> initExpectedCountryLists() {
//        return IntStream.range(0, CountryDao.COUNTRY_INIT_DATA.length)
//                .mapToObj(i -> Tuple.of(i, CountryDao.COUNTRY_INIT_DATA[i]))
//                .map(intStringTuple -> new SimpleCountry(
//                        intStringTuple._1,
//                        intStringTuple._2[0],
//                        intStringTuple._2[1]))
//                .peek(expectedCountryList::add)
//                .filter(simpleCountry -> simpleCountry.getName().startsWith("A"))
//                .collect(Collectors.toList());
//    }

}