package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import lab.dao.CountryDao;
import org.springframework.stereotype.Repository;

import lab.model.Country;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
//		TODO: Implement it
		EntityManager em = null;

		if (em != null) {
			em.close();
		}
	}

	@Override
	public List<Country> getAllCountries() {
//	TODO: Implement it
		return null	;
	}// getAllcountries()

	@Override
	public Country getCountryByName(String name) {
//		TODO: Implement it

		return null;
	}

}
