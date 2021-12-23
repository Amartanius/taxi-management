package fr.bcg.taximanagement.implementation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.bcg.taximanagement.entity.Ride;
import fr.bcg.taximanagement.exception.RideNotFoundException;
import fr.bcg.taximanagement.model.RideDetail;
import fr.bcg.taximanagement.model.RideMin;
import fr.bcg.taximanagement.model.Status;
import fr.bcg.taximanagement.repository.RideRepository;
import fr.bcg.taximanagement.service.FormulaCalculatorService;
import fr.bcg.taximanagement.service.RidesService;

@Service
public class RidesServiceImpl implements RidesService {

	@Autowired
	private RideRepository rideRepo;

	@Autowired
	private FormulaCalculatorService formulaCalculatorService;
	
	Logger logger = LoggerFactory.getLogger(RidesServiceImpl.class);

	@Override
	public RideDetail getRideDetails(Long id) {
		Optional<Ride> ride = rideRepo.findById(id);
		if (ride.isPresent()) {
			RideDetail rideDetail = new RideDetail();
			BeanUtils.copyProperties(ride.get(), rideDetail);
			if (ride.get().getStatus().equals(Status.ARRIVED.toString()) && ride.get().getCharge() == null) {
				logger.info("Ride with id {} had arrived without charge , calculating.", ride.get().getId());
				double charge = formulaCalculatorService.calculateCharge(ride.get());
				rideDetail.setCharge(charge);
				logger.info("Ride with id {} had been charged, updating the Database.", ride.get().getId());
				updateCharge(ride.get(), charge);
			}
			return rideDetail;

		} else {
			logger.error("Ride not found with id : {}", id);
			throw new RideNotFoundException();
		}
	}

	@Override
	public Page<RideMin> getAllRidesPaginated(Integer pageNo, Integer pageSize, String sortBy, String sortOrder,
			String driver) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));
		Page<Ride> pageResult = rideRepo.findByDriverContaining(driver, paging);
		Page<RideMin> page = new PageImpl<>(pageResult.getContent().stream()
				.map(u -> new RideMin(u.getId(), u.getDriver(), u.getStartTime(), u.getStatus(), u.getDistance()))
				.collect(Collectors.toList()), pageResult.getPageable(), pageResult.getTotalElements());
		return page;
	}
	
	private void updateCharge(Ride ride, double charge) {
		ride.setCharge(charge);
		rideRepo.save(ride);
	}

}
