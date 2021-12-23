package fr.bcg.taximanagement.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.bcg.taximanagement.entity.Formula;
import fr.bcg.taximanagement.entity.Ride;
import fr.bcg.taximanagement.exception.BadFormulaException;
import fr.bcg.taximanagement.exception.FormulaNotFoundException;
import fr.bcg.taximanagement.repository.FormulaRepository;
import fr.bcg.taximanagement.service.FormulaCalculatorService;

@SpringBootTest
public class FormulaCalculatorImplTest {

	@MockBean
	private FormulaRepository FormulaRepositoryMock;
	
	@Autowired
	private FormulaCalculatorService formulaCalculatorService;
	
	@Test
	public void calculateChargeTest(){
		Formula formula1 = new Formula();
		formula1.setCityId("PARIS");
		formula1.setFormula("(1.0 + ( 2.5 * Distance) + ( (#hour >= 6 && #hour < 20) ? 0 : 0.5) + ((#hour >= 16 && #hour < 19) ? 1 : 0))");
		
		Formula formula2 = new Formula();
		formula2.setCityId("NANCY");
		formula2.setFormula("#unknownvariable + 2");

		Ride ride1 = new Ride();
		ride1.setCityId("PARIS");
		ride1.setStartTime(new GregorianCalendar(2014, Calendar.FEBRUARY, 11 , 17 , 10 , 10 ).getTime());
		ride1.setDistance(19.0);
		
		Ride ride2 = new Ride();
		ride2.setCityId("NANCY");
		ride2.setStartTime(new GregorianCalendar(2014, Calendar.FEBRUARY, 11 , 17 , 10 , 10 ).getTime());
		ride2.setDistance(19.0);
		
		Ride ride3 = new Ride();
		ride3.setCityId("Lyon");
		ride3.setStartTime(new GregorianCalendar(2014, Calendar.FEBRUARY, 11 , 17 , 10 , 10 ).getTime());
		ride3.setDistance(19.0);
		
		when(FormulaRepositoryMock.findByCityId("PARIS")).thenReturn(Optional.of(formula1));
		when(FormulaRepositoryMock.findByCityId("NANCY")).thenReturn(Optional.of(formula2));
		when(FormulaRepositoryMock.findByCityId("LYON")).thenReturn(Optional.empty());

		assertThat(formulaCalculatorService.calculateCharge(ride1)).isEqualTo((double) 49.5);
		assertThatExceptionOfType(BadFormulaException.class)
		  .isThrownBy(() -> {
			  formulaCalculatorService.calculateCharge(ride2);
		});
		assertThatExceptionOfType(FormulaNotFoundException.class)
		  .isThrownBy(() -> {
			  formulaCalculatorService.calculateCharge(ride3);
		});
	}
}
