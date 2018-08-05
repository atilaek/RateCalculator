package aek.demo.zopa.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import aek.demo.zopa.service.LenderService;
import aek.demo.zopa.service.repository.RateResourcesBundle;

public class BarrowerTest {
    private static RateResourcesBundle rateResourcesBundle = new RateResourcesBundle("rateResources");
    private static final int VIEW_AVERAGE_RATE_SCALE = Integer.
            parseInt(rateResourcesBundle.getViewAverageRateScale());
    private static final int VIEW_MONTHLY_REPAYMENT_SCALE = Integer.
            parseInt(rateResourcesBundle.getViewMonthlyRepaymentScale());
    private static final int VIEW_TOTAL_PAYMENT_SCALE = Integer.
            parseInt(rateResourcesBundle.getViewTotalPaymentScale());

    private Borrower borrower;

    @Before
    public void setUp() throws Exception {
        LenderService lenderService = new LenderService("src/test/resources/marketDataTest.csv");
        borrower = new Borrower(1100, lenderService);
    }

    @Test
    public void getRequestedAmount_ShouldReturnExpectedRequestedAmount() {
        assertEquals(1100, borrower.getRequestedAmount());
    }

    @Test
    public void getAverageRateWithWithScale_ShouldReturnExpectedAverageRateWithCorrectScale() {
        assertEquals(new BigDecimal(7.0).setScale(VIEW_AVERAGE_RATE_SCALE, BigDecimal.ROUND_HALF_UP),
                     borrower.getAverageRateWithScale());
    }

    @Test
    public void getMonthlyRepaymentWithScale_ShouldReturnExpectedgetMonthlyRepaymentWithCorrectScale() {
        assertEquals(new BigDecimal(32.70).setScale(VIEW_MONTHLY_REPAYMENT_SCALE, BigDecimal.ROUND_HALF_UP),
                     borrower.getMonthlyRepaymentWithScale());
    }

    @Test
    public void getTotalRepaymentWithScale_ShouldReturnExpectedgetotalRepaymentWithCorrectScale() {
        assertEquals(new BigDecimal(1177.26).setScale(VIEW_TOTAL_PAYMENT_SCALE, BigDecimal.ROUND_HALF_UP),
                     borrower.getTotalRepaymentWithScale());
    }

    @Test
    public void getRequiredLenders_ShouldReturnExpectedListOfLendersInSize() {
        assertEquals(4, borrower.getRequiredLenders().size());
    }

}
