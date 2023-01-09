package br.nom.dailton.evalspringcacheaspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EvalSpringCacheAspectApplicationDBTests {
	@Autowired
	private CountryService countryService;

	@Test
	void countryService_ins1() {
		assertThatThrownBy(() -> countryService.insertTwoCountries1("f", "s"))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("FORCED EXCEPTION");
		assertThat(countryService.getAllCountries()).hasSize(4);
	}
	@Test
	void countryService_ins2() {
		assertThatThrownBy(() -> countryService.insertTwoCountries2("f", "s"))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("FORCED EXCEPTION");
		assertThat(countryService.getAllCountries()).hasSize(4); //DOES NOT PASS
	}
	@Test
	void countryService_ins3() {
		assertThatThrownBy(() -> countryService.insertTwoCountries3("f", "s"))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("FORCED EXCEPTION");
		Utils.sleepSeconds(2L);
		assertThat(countryService.getAllCountries()).hasSize(4);
	}

}
