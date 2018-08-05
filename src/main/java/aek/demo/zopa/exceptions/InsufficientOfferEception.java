package aek.demo.zopa.exceptions;

/**
 * Throws a controlled exception when
 * the total offer amount is insufficient
 * thrown at @{@link aek.demo.zopa.service.CalculationService}
 * Captured at @{@RateCalculator}.
 *
 * @author Atila Ekimci
 */
public class InsufficientOfferEception extends Exception {

    public InsufficientOfferEception(String message) {
        super(message);
    }
}

