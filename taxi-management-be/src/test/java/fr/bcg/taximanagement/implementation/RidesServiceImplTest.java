package fr.bcg.taximanagement.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fr.bcg.taximanagement.entity.Ride;
import fr.bcg.taximanagement.exception.RideNotFoundException;
import fr.bcg.taximanagement.model.RideMin;
import fr.bcg.taximanagement.model.Status;
import fr.bcg.taximanagement.repository.RideRepository;
import fr.bcg.taximanagement.service.FormulaCalculatorService;
import fr.bcg.taximanagement.service.RidesService;

@SpringBootTest
public class RidesServiceImplTest {

	@MockBean
	private RideRepository rideRepoMock;
	
	@MockBean
	private FormulaCalculatorService formulaCalculatorServiceMock;
	
	@Autowired
	private RidesService ridesService;
	
	@Test
	public void getRideDetailsTest() {
		Ride ride1 = new Ride();
		ride1.setCharge(null);
		ride1.setStatus(Status.STARTED.toString());
		Ride ride2 = new Ride();
		ride2.setCharge(null);
		ride2.setStatus(Status.ARRIVED.toString());
		ride2.setDistance(10.0);
		ride2.setStartTime(new GregorianCalendar(2014, Calendar.FEBRUARY, 11 , 17 , 10 , 10 ).getTime());
		when(rideRepoMock.findById((long) 1)).thenReturn(Optional.of(ride1));
		when(rideRepoMock.findById((long) 2)).thenReturn(Optional.of(ride2));
		when(rideRepoMock.findById((long) 3)).thenReturn(Optional.<Ride>empty());
		when(formulaCalculatorServiceMock.calculateCharge(ride2)).thenReturn(2.4);
		assertThat(ridesService.getRideDetails((long) 1).getCharge()).isNull();
		assertThat(ridesService.getRideDetails((long) 2).getCharge()).isEqualTo((double) 2.4);
		assertThatExceptionOfType(RideNotFoundException.class)
		  .isThrownBy(() -> {
			  ridesService.getRideDetails((long) 3);
		});
	}
	
	@Test
	public void getAllRidesPaginatedTest() {
		Ride ride1 = new Ride();
		ride1.setCharge(null);
		ride1.setStatus(Status.STARTED.toString());
		Ride ride2 = new Ride();
		ride2.setCharge(null);
		ride2.setStatus(Status.ARRIVED.toString());
		ride2.setDistance(10.0);
		ride2.setStartTime(new GregorianCalendar(2014, Calendar.FEBRUARY, 11 , 17 , 10 , 10 ).getTime());
		Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.valueOf("DESC"), "startTime"));
		List<Ride> rides = new ArrayList<>();
		rides.add(ride1);
		rides.add(ride2);
		when(rideRepoMock.findByDriverContaining("na", paging)).thenReturn(new PageImpl<Ride>(rides));
		assertThat(ridesService.getAllRidesPaginated(0, 10, "startTime", "DESC", "na")).isInstanceOf(Page.class);
		RideMin rideComparable = new RideMin();
		rideComparable.setStatus(Status.STARTED.toString());
		assertThat(ridesService.getAllRidesPaginated(0, 10,  "startTime","DESC", "na")).anyMatch(s -> s.getStatus().equals(Status.ARRIVED.toString()));

	}
	

}
