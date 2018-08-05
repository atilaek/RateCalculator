package aek.demo.zopa.service.repository;

import java.util.ResourceBundle;

/**
 * The Bundle class that gets all properties from rateResources.properties
 *
 * @author Atila Ekimci
 */
public class RateResourcesBundle {

    private static final String minimum_loan = "MIN_LOAN";
    private static final String minimum_loan_error_msg = "MIN_LOAN_ERROR_MSG";
    private static final String maximum_loan = "MAX_LOAN";
    private static final String maximum_loan_error_msg = "MAX_LOAN_ERROR_MSG";
    private static final String multiples_of_loan = "MULTIPLES_OF";
    private static final String multiples_of_loan_error_msg = "MULTIPLES_OF_ERROR_MSG";
    private static final String number_of_months_for_loan = "NUMBER_MONTHS_LOANS";
    private static final String bigdecimal_calculation_scale = "BIGDECIMAL_CALCULATION_SCALE";
    private static final String insufficient_offer_error_msg = "INSUFFICIENT_OFFER_ERROR_MSG";
    private static final String invalid_input_error_msg = "INVALID_INPUT_ERROR_MSG";
    private static final String invalid_filemap_error_msg = "INVALID_FILEMAP_ERROR_MSG";
    private static final String view_average_rate_scale = "VIEW_AVERAGE_RATE_SCALE";
    private static final String view_monthly_repayment_scale = "VIEW_MONTHLY_REPAYMENT_SCALE";
    private static final String view_total_payment_scale = "VIEW_TOTAL_PAYMENT_SCALE";

    private ResourceBundle resourceBundle;

    public RateResourcesBundle(final String bundleName) {
        resourceBundle = ResourceBundle.getBundle(bundleName);
    }

    @SuppressWarnings("unused")
    public RateResourcesBundle() {
    }

    private String getProperty(final String propertyId) {
        return resourceBundle.getString(propertyId);
    }

    public String getMinimumLoan() {
        return getProperty(minimum_loan);
    }

    public String getMinimumLoanErrorMsg() {
        return getProperty(minimum_loan_error_msg);
    }

    public String getMaximumLoan() {
        return getProperty(maximum_loan);
    }

    public String getMaximumLoanErrorMsg() {
        return getProperty(maximum_loan_error_msg);
    }

    public String getMultiplesOfLoan() {
        return getProperty(multiples_of_loan);
    }

    public String getMultiplesOfLoanErrorMsg() {
        return getProperty(multiples_of_loan_error_msg);
    }

    public String getNumberOfMonthsForLoan() {
        return getProperty(number_of_months_for_loan);
    }

    public String getBigDecimalCalculationScale() {
        return getProperty(bigdecimal_calculation_scale);
    }

    public String getInsufficientOfferErrorMsg() {
        return getProperty(insufficient_offer_error_msg);
    }

    public String getInvalidInputErrorMsg() {
        return getProperty(invalid_input_error_msg);
    }

    public String getInvalidFilemapErrorMsg() {
        return getProperty(invalid_filemap_error_msg);
    }

    public String getViewAverageRateScale() {
        return getProperty(view_average_rate_scale);
    }

    public String getViewMonthlyRepaymentScale() {
        return getProperty(view_monthly_repayment_scale);
    }

    public String getViewTotalPaymentScale() {
        return getProperty(view_total_payment_scale);
    }

}
