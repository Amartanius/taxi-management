package fr.bcg.taximanagement.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.bcg.taximanagement.model.RideDetail;
import fr.bcg.taximanagement.model.RideMin;
import fr.bcg.taximanagement.service.RidesService;


@WebMvcTest(TaxiManagemenController.class)
public class TaxiManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RidesService ridesService;

	@Test
	public void getAllRidesShouldReturnAPage() throws Exception {
		List<RideMin> ridesList = new ArrayList<>();
		Page<RideMin> page = new PageImpl<RideMin>(ridesList);
		when(ridesService.getAllRidesPaginated(0, 10, "startTime", "DESC", ""))
				.thenReturn(page);
		this.mockMvc.perform(get("/api/rides?pageNo=0")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.pageable", is("INSTANCE")));
	}
	
	@Test
	public void getRideDetailsShouldReturnDetails() throws Exception {
		RideDetail rideDetails = new RideDetail();
		when(ridesService.getRideDetails((long) 3)).thenReturn(rideDetails);
		this.mockMvc.perform(get("/api/rides/3")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
