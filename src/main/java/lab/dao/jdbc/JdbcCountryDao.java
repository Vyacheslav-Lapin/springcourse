package lab.dao.jdbc;

import lab.dao.CountryDao;
import lab.dao.CountryNotFoundException;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static lab.dao.jdbc.JdbcDao.executeAndGetKey;
import static lab.dao.jdbc.JdbcDao.getPreparedStatementCreator;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {

    public static String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Russian Federation", "RU"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}};

    static String INSERT_COUNTRY = "insert into country (name, code_name) values (?,?)";
    static String GET_ALL_COUNTRIES = "select * from country";
    static String GET_COUNTRIES_BY_NAME_LIKE = "select * from country where name like :name";
    static String GET_COUNTRY_BY_NAME = "select * from country where name = ?";
    static String GET_COUNTRY_BY_CODE_NAME = "select * from country where code_name = '%s'";
    static String UPDATE_COUNTRY_NAME = "update country set name='%s' where code_name='%s'";

    static RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, __) -> new SimpleCountry()
            .setId(rs.getInt("id"))
            .setName(rs.getString("name"))
            .setCodeName(rs.getString("code_name"));

    public JdbcCountryDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void save(Country country) {
        long countryId = create(country.getName(), country.getCodeName());
        country.setId(countryId);
    }

    private long create(String name, String codeName) {
        PreparedStatementCreator preparedStatementCreator =
                getPreparedStatementCreator(INSERT_COUNTRY, name, codeName);

        return executeAndGetKey(keyHolder -> getJdbcTemplate().update(
                preparedStatementCreator, keyHolder));
    }

    @Override
    public List<Country> getAllCountries() {
        return getJdbcTemplate()
                .query(GET_ALL_COUNTRIES, COUNTRY_ROW_MAPPER);
    }

    public List<Country> getCountryListStartWith(String name) {
        return getNamedParameterJdbcTemplate().query(
                GET_COUNTRIES_BY_NAME_LIKE,
                new MapSqlParameterSource("name", name + "%"),
                COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        getJdbcTemplate().execute(
                String.format(UPDATE_COUNTRY_NAME, newCountryName, codeName));
    }

    public void loadCountries() {
        Arrays.stream(COUNTRY_INIT_DATA)
                .forEach(strings -> create(strings[0], strings[1]));
    }

    public Country getCountryByCodeName(String codeName) {
        return getJdbcTemplate().query(
                String.format(GET_COUNTRY_BY_CODE_NAME, codeName),
                COUNTRY_ROW_MAPPER)
                .get(0);
    }

    @Override
    public Country getCountryByName(String name) {
        try {
            return getJdbcTemplate().query(
                    String.format(GET_COUNTRY_BY_NAME, name),
                    COUNTRY_ROW_MAPPER).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CountryNotFoundException(e);
        }
    }
}
