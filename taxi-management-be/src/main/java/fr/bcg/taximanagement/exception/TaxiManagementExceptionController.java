package fr.bcg.taximanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaxiManagementExceptionController {

	@ExceptionHandler(value = RideNotFoundException.class)
	public ResponseEntity<Object> rideNotFoundException(RideNotFoundException exception) {
		return new ResponseEntity<>("Ride not found.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = FormulaNotFoundException.class)
	public ResponseEntity<Object> formulaNotFoundException(FormulaNotFoundException exception) {
		return new ResponseEntity<>("Cannot calculate charge for your city. Please contact your administrator", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = BadFormulaException.class)
	public ResponseEntity<Object> badFormulaException(BadFormulaException exception) {
		return new ResponseEntity<>("Bad Formula used for your city. Please contact your administrator", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
