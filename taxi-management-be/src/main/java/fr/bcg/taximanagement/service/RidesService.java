package fr.bcg.taximanagement.service;

import org.springframework.data.domain.Page;

import fr.bcg.taximanagement.model.RideDetail;
import fr.bcg.taximanagement.model.RideMin;

public interface RidesService {
	
	RideDetail getRideDetails(Long id);

	Page<RideMin> getAllRidesPaginated(Integer pageNo, Integer pageSize, String sortBy, String sortOrder,
			String driver);
}
