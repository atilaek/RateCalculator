package aek.demo.zopa.service.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import aek.demo.zopa.model.Lender;

/**
 * CVS file reading class that has static file reading function.
 * Used by LenderService.
 *
 * @author Atila Ekimci
 */
public class CvsFileReader {

    private static final String COMMA = ",";

    /**
     * Reads CSV file through the file path and put them in a @{@link List} of @{@link Lender}.
     *
     * @param inputFilePath The file path that has csv file to read
     * @return @{@link List} of @{@link Lender}
     * @throws FileNotFoundException    if the file path is not refering to an actual file
     */
    public static List<Lender> readCsvFile(String inputFilePath) throws FileNotFoundException {

        List<Lender> inputList = new ArrayList<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFs = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFs));
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    private static Function<String, Lender> mapToItem = (line) -> {
        String[] lenderString = line.split(COMMA);
        return new Lender(lenderString[0], new BigDecimal(lenderString[1]), Integer.parseInt(lenderString[2]));
    };

}
