package fr.bcg.taximanagement.service;

import fr.bcg.taximanagement.entity.Ride;

public interface FormulaCalculatorService {

	Double calculateCharge(Ride ride);
}
