package fr.bcg.taximanagement.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditorAwareImplTest {

	@Autowired
	private AuditorAwareImpl auditorAwareImplTest;

	@Test
	public void getCurrentAuditorTest() {
		assertThat(auditorAwareImplTest.getCurrentAuditor()).isEqualTo(Optional.of("Elhoussine"));
	}
}
