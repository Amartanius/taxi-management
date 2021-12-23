package fr.bcg.taximanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import fr.bcg.taximanagement.controller.TaxiManagemenController;

@SpringBootTest
class TaximanagementApplicationTests {

	@Autowired 
	private TaxiManagemenController taxiManagemenController;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(taxiManagemenController).isNotNull();
	}

}
