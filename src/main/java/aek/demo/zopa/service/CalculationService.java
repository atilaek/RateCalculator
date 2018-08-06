package aek.demo.zopa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import aek.demo.zopa.exceptions.InsufficientOfferEception;
import aek.demo.zopa.model.Lender;
import aek.demo.zopa.service.repository.RateResourcesBundle;

/**
 * The class used by {@link aek.demo.zopa.model.Borrower}
 * It calculates averageRate,  monthlyRepayment, totalRepayment
 * and requiredLenders for the borrower.
 *
 * @author Atila Ekimci
 */
public class CalculationService {

    private static RateResourcesBundle rateResourcesBundle = new RateResourcesBundle("rateResources");
    private static final int BIGDECIMAL_CALCULATION_SCALE = Integer
            .parseInt(rateResourcesBundle.getBigDecimalCalculationScale());
    private static final String INSUFFICIENT_OFFER_ERROR_MSG = rateResourcesBundle.getInsufficientOfferErrorMsg();

    /**
     * The method is for finding required exact amounts from each lender needed.
     * Mark that the priority starts from the lender with lowest interest rate.
     *
     * @param requestedAmount       the amoaunt requested
     * @param lenderService         the service class holding all
     *                              lenders

     * @return requiredLenders      if total amounts from all
     *                              lenders is sufficient;
     *
     * @throws InsufficientOfferEception  if the sum of offer amounts from all lenders
     *                                    is not sufficient.
     */
    public static List<Lender> findRequiredLendersWithAmounts(final int requestedAmount,
                                                              final LenderService lenderService)
            throws InsufficientOfferEception {
        int remainingLoan = requestedAmount;
        List<Lender> requiredLenders = new ArrayList<>();
        if (requestedAmount > lenderService.getPossibleMaxLenderOffer()) {
            throw new InsufficientOfferEception(INSUFFICIENT_OFFER_ERROR_MSG);
        }
        for (Lender lender : lenderService.getLenders()) {
            if (remainingLoan > lender.getAvailableAmount()) {
                remainingLoan -= lender.getAvailableAmount();
                requiredLenders.add(lender);
            } else {
                requiredLenders.add(new Lender(lender.getName(), lender.getRate(), remainingLoan));
                break;
            }
        }
        return requiredLenders;
    }

    /**
     * Calculates average interest rate
     * by dividing @totalRepayment to @requestedAmount
     * and finds the percentage by multiplying to 100 and then subtracting 100.
     *
     * @param totalRepayment Total amount to be paid back by Borrower
     * @param requestedAmount Requested amount for loan by Borrower
     * @return averageRate
     */
    public static BigDecimal calculateAverageRate(final BigDecimal totalRepayment, final BigDecimal requestedAmount) {
        return totalRepayment.divide(requestedAmount, BIGDECIMAL_CALCULATION_SCALE, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100)).subtract(new BigDecimal(100));
    }

    /**
     * Calculates total repayment amount by summing all amounts in
     * required lenders list.
     *
     * @param requiredLenders @{@link List} of @{@link Lender} for the loan
     * @return totalRepayment Total amount to be paid back by Borrower
     */
    public static BigDecimal calculateTotalRepayment(final List<Lender> requiredLenders) {
        BigDecimal totalRepayment = new BigDecimal(0);
        for (Lender lender : requiredLenders) {
            totalRepayment = totalRepayment.add(
                    BigDecimal.valueOf(lender.getAvailableAmount())
                            .multiply(BigDecimal.ONE.add(lender.getRate())));
        }
        return totalRepayment;
    }

    /**
     * Calculates monthly repayment amount by dividing total repayment to
     * months per loan.
     *
     * @param totalRepayment Total amount to be paid back by Borrower
     * @param loanMonths the amount of months for payback installment
     * @return monthlyRepayment
     */
    public static BigDecimal calculateMonthlyRepayment(final BigDecimal totalRepayment, final BigDecimal loanMonths) {
        return totalRepayment.divide(loanMonths, BIGDECIMAL_CALCULATION_SCALE, BigDecimal.ROUND_HALF_UP);
    }
}
