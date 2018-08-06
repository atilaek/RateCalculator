import java.io.FileNotFoundException;

import aek.demo.zopa.exceptions.InsufficientOfferEception;
import aek.demo.zopa.model.Borrower;
import aek.demo.zopa.service.LenderService;
import aek.demo.zopa.service.repository.RateResourcesBundle;

/**
 * Main Class that takes two command line inputs.
 * 1st input as csv file holding market data that has offers for loans as list.
 * 2nd input as the amoun borrower wants to loan.
 *
 * @author Atila Ekimci
 */
public class RateCalculator {

    private static final RateResourcesBundle rateResourcesBundle = new RateResourcesBundle("rateResources");
    private static final int MIN_LOAN = Integer.parseInt(rateResourcesBundle.getMinimumLoan());
    private static final String MIN_LOAN_ERROR_MSG = rateResourcesBundle.getMinimumLoanErrorMsg();
    private static final int MAX_LOAN = Integer.parseInt(rateResourcesBundle.getMaximumLoan());
    private static final String MAX_LOAN_ERROR_MSG = rateResourcesBundle.getMaximumLoanErrorMsg();
    private static final int MULTIPLES_OF = Integer.parseInt(rateResourcesBundle.getMultiplesOfLoan());
    private static final String MULTIPLES_OF_ERROR_MSG = rateResourcesBundle.getMultiplesOfLoanErrorMsg()
            + MULTIPLES_OF + "!";
    private static final String INVALID_INPUT_ERROR_MSG = rateResourcesBundle.getInvalidInputErrorMsg();
    private static final String INVALID_FILEMAP_ERROR_MSG = rateResourcesBundle.getInvalidFilemapErrorMsg();

    /**
     * Main function that calculates and prints loan repayment details.
     *
     * @param args list of arguments that has to hold location of cvs file and loan amount
     */
    public static void main(String[] args) {
        System.out.println("--------PROGRAM STARTED--------");

        try {
            int loan = Integer.valueOf(args[1]);

            if (isLoanValid(loan)) {
                String cvsFileLocation = args[0];
                LenderService lenderService = new LenderService(cvsFileLocation);

                Borrower borrowerInst = new Borrower(loan, lenderService);

                System.out.println(borrowerInst.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_ERROR_MSG);
            System.out.println(e.toString());
        } catch (FileNotFoundException e) {
            System.out.println(INVALID_FILEMAP_ERROR_MSG);
            System.out.println(e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(INVALID_INPUT_ERROR_MSG);
        } catch (InsufficientOfferEception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------PROGRAM FINISHED--------");
    }

    private static boolean isLoanValid(int loan) {
        if (MIN_LOAN > loan) {
            System.out.println(MIN_LOAN_ERROR_MSG);
            return false;
        } else if (loan > MAX_LOAN) {
            System.out.println(MAX_LOAN_ERROR_MSG);
            return false;
        } else if (loan % MULTIPLES_OF != 0) {
            System.out.println(MULTIPLES_OF_ERROR_MSG);
            return false;
        }
        return true;
    }
}
