package br.nom.dailton.evalspringcacheaspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class EvalSpringCacheAspectApplicationCacheTests {
	@Autowired
	private MyService myService;

	@Test
	void myService_myFirstWork() {
		long start = System.currentTimeMillis();

		String r1 = myService.myFirstWork();
		String r2 = myService.myFirstWork();

		long end = System.currentTimeMillis();
		long elapsedTime = end - start;

		assertThat(r2).isEqualTo(r1);
		assertThat(elapsedTime).isBetween(2000L, 3000L);
	}
	@Test
	void myService_mySecondWork() {
		long start = System.currentTimeMillis();

		String r1 = myService.mySecondWork();
		String r2 = myService.mySecondWork();

		long end = System.currentTimeMillis();
		long elapsedTime = end - start;

		assertThat(r2).isEqualTo(r1);
		assertThat(elapsedTime).isBetween(2000L, 3000L); //DOES NOT PASS
	}
	@Test
	void myService_myThirdWork() {
		long start = System.currentTimeMillis();

		String r1 = myService.myThirdWork();
		String r2 = myService.myThirdWork();

		long end = System.currentTimeMillis();
		long elapsedTime = end - start;

		assertThat(r2).isEqualTo(r1);
		assertThat(elapsedTime).isBetween(2000L, 3000L);
	}

}
