package aek.demo.zopa.model;

import java.math.BigDecimal;
import java.util.List;

import aek.demo.zopa.exceptions.InsufficientOfferEception;
import aek.demo.zopa.service.CalculationService;
import aek.demo.zopa.service.LenderService;
import aek.demo.zopa.service.repository.RateResourcesBundle;

/**
 * The class representing the borrower who requests a loan.
 *
 * @author Atila Ekimci
 */
public class Borrower {

    private static RateResourcesBundle rateResourcesBundle = new RateResourcesBundle("rateResources");
    private static final int NUMBER_MONTHS_LOANS = Integer
            .parseInt(rateResourcesBundle.getNumberOfMonthsForLoan());
    private static final int VIEW_AVERAGE_RATE_SCALE = Integer
            .parseInt(rateResourcesBundle.getViewAverageRateScale());
    private static final int VIEW_MONTHLY_REPAYMENT_SCALE = Integer
            .parseInt(rateResourcesBundle.getViewMonthlyRepaymentScale());
    private static final int VIEW_TOTAL_PAYMENT_SCALE = Integer
            .parseInt(rateResourcesBundle.getViewTotalPaymentScale());

    private int requestedAmount;
    private BigDecimal averageRate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;
    private List<Lender> requiredLenders;

    /**
     * Main Constructor for Borrower class that calculates automatically.
     *
     * @param loan          amount of loan the borrower wants to have
     * @param lenderService list of lenders that can give loan with
     *                      different rates.
     * @throws InsufficientOfferEception if the LenderService doesn't
     *                                   have enough resources to loan.
     */
    public Borrower(final int loan, final LenderService lenderService) throws InsufficientOfferEception {
        this.requestedAmount = loan;
        this.requiredLenders = CalculationService.findRequiredLendersWithAmounts(requestedAmount, lenderService);
        this.totalRepayment = CalculationService.calculateTotalRepayment(requiredLenders);
        this.monthlyRepayment = CalculationService
                .calculateMonthlyRepayment(totalRepayment, BigDecimal.valueOf(NUMBER_MONTHS_LOANS));
        this.averageRate = CalculationService
                .calculateAverageRate(totalRepayment, BigDecimal.valueOf(requestedAmount));
    }

    @Override
    public String toString() {
        return "requestedAmount: £" + requestedAmount + "\n"
                + "averageRate: " + averageRate
                .setScale(VIEW_AVERAGE_RATE_SCALE, BigDecimal.ROUND_HALF_UP) + "%\n"
                + "monthlyRepayment: £" + monthlyRepayment
                .setScale(VIEW_MONTHLY_REPAYMENT_SCALE, BigDecimal.ROUND_HALF_UP) + "\n"
                + "totalRepayment: £" + totalRepayment
                .setScale(VIEW_TOTAL_PAYMENT_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    int getRequestedAmount() {
        return requestedAmount;
    }

    BigDecimal getAverageRateWithScale() {
        return averageRate.setScale(VIEW_AVERAGE_RATE_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    BigDecimal getMonthlyRepaymentWithScale() {
        return monthlyRepayment.setScale(VIEW_MONTHLY_REPAYMENT_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    BigDecimal getTotalRepaymentWithScale() {
        return totalRepayment.setScale(VIEW_TOTAL_PAYMENT_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    List<Lender> getRequiredLenders() {
        return requiredLenders;
    }
}
