package fr.bcg.taximanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.bcg.taximanagement.model.RideDetail;
import fr.bcg.taximanagement.model.RideMin;
import fr.bcg.taximanagement.service.RidesService;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class TaxiManagemenController {

	@Autowired
	private RidesService ridesService;
	
    Logger logger = LoggerFactory.getLogger(TaxiManagemenController.class);
	
	@GetMapping("/rides")
	public ResponseEntity<Page<RideMin>> getAllRides(@RequestParam(required = true) Integer pageNo, 
            @RequestParam(defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(defaultValue = "startTime",required = false) String sortBy,
            @RequestParam(defaultValue = "DESC",required = false) String sortOrder,
            @RequestParam(defaultValue = "", required = false) String driver) {
		logger.info("Search Request Received for searchTerm {} ", driver);
		Page<RideMin> pageDetails = ridesService.getAllRidesPaginated(pageNo,pageSize,sortBy,sortOrder,driver);
		return new ResponseEntity<Page<RideMin>>(pageDetails , HttpStatus.OK);
	}
	
	@GetMapping("/rides/{id}")
	public ResponseEntity<RideDetail> getRideDetails(@PathVariable(required = true) Long id) {
		RideDetail ridedetail = ridesService.getRideDetails(id);
		logger.info("Ride Details request Received for Ride with ID : {}", id);
		return new ResponseEntity<RideDetail>(ridedetail , HttpStatus.OK);
	}
}
