package fr.bcg.taximanagement.implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import fr.bcg.taximanagement.entity.Formula;
import fr.bcg.taximanagement.entity.Ride;
import fr.bcg.taximanagement.exception.BadFormulaException;
import fr.bcg.taximanagement.exception.FormulaNotFoundException;
import fr.bcg.taximanagement.repository.FormulaRepository;
import fr.bcg.taximanagement.service.FormulaCalculatorService;

@Service
public class FormulaCalculatorImpl implements FormulaCalculatorService {

	@Autowired
	private FormulaRepository formulaRepo;

	Logger logger = LoggerFactory.getLogger(FormulaCalculatorImpl.class);

	@Override
	public Double calculateCharge(Ride ride) {
		Optional<Formula> formula = formulaRepo.findByCityId(ride.getCityId());
		if (formula.isPresent()) {
			ExpressionParser parser = new SpelExpressionParser();
			formula.get().getFormula();
			Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(ride.getStartTime()); // assigns calendar to given date
			StandardEvaluationContext context = new StandardEvaluationContext(ride);
			context.setVariable("hour", calendar.get(Calendar.HOUR_OF_DAY));
			BigDecimal bd;
			try {
				double charge = parser.parseExpression(formula.get().getFormula()).getValue(context, Double.class);
				bd = new BigDecimal(charge).setScale(2, RoundingMode.HALF_UP);

			} catch (ParseException | SpelEvaluationException e2) {
				logger.error("Error Parsing the formula : {}", formula.get().getFormula());
				throw new BadFormulaException();
			}
			logger.info("Charge Calculated for Ride : {}", ride.getId());
			return bd.doubleValue();
		} else {
			logger.error("Formula not found for city : {}", ride.getCityId());
			throw new FormulaNotFoundException();
		}
	}

}
