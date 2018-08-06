package aek.demo.zopa.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aek.demo.zopa.model.Lender;
import aek.demo.zopa.service.repository.CvsFileReader;

/**
 * Lender Service class that gets and sorts @{@link List} of @{@link Lender} through file location
 * used by @{@link CalculationService}.
 *
 * @author Atila Ekimci
 */

public class LenderService {

    private List<Lender> lenders = new ArrayList<>();
    private int possibleMaxLenderOffer = 0;

    /**
     * Main LenderService constructor that lists Lenders from lowest interest rate to highest
     * along with maximum loan amount from total lenders.
     *
     * @param cvsFileLocation The file location path that has csv file to read
     * @throws FileNotFoundException The exception coming from @{@link CvsFileReader}
     *                               and passed through to Main class RateCalculator.
     *
     */
    public LenderService(final String cvsFileLocation) throws FileNotFoundException {
        this.lenders = CvsFileReader.readCsvFile(cvsFileLocation);
        sortLendersFromLowestRate();
        possibleMaxLenderOffer = lenders.stream().mapToInt(o -> o.getAvailableAmount()).sum();
    }

    int getPossibleMaxLenderOffer() {
        return possibleMaxLenderOffer;
    }

    List<Lender> getLenders() {
        return lenders;
    }

    private void sortLendersFromLowestRate() {
        Collections.sort(this.lenders);
    }
}
