package aek.demo.zopa.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import aek.demo.zopa.model.Lender;

public class LenderServiceTest {

    private LenderService lenderService;
    List<Lender> allLenders = new ArrayList<>();
    List<Lender> requiredLenders = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        lenderService = new LenderService("src/test/resources/marketDataTest.csv");

        allLenders.add(new Lender("Jane", BigDecimal.valueOf(0.069), 480));
        allLenders.add(new Lender("Fred", BigDecimal.valueOf(0.071), 520));
        allLenders.add(new Lender("Angela", BigDecimal.valueOf(0.071), 60));
        allLenders.add(new Lender("Dave", BigDecimal.valueOf(0.074), 140));
        allLenders.add(new Lender("Bob", BigDecimal.valueOf(0.075), 640));
        allLenders.add(new Lender("John", BigDecimal.valueOf(0.081), 320));
        allLenders.add(new Lender("Mary", BigDecimal.valueOf(0.104), 170));

        requiredLenders.add(new Lender("Jane", BigDecimal.valueOf(0.069), 480));
        requiredLenders.add(new Lender("Fred", BigDecimal.valueOf(0.071), 520));
        requiredLenders.add(new Lender("Angela", BigDecimal.valueOf(0.071), 60));
        requiredLenders.add(new Lender("Dave", BigDecimal.valueOf(0.074), 40));
    }

    @Test
    public void getLenders_marketDataTestGiven_ShouldReturnExpectedListOfLenders() throws FileNotFoundException {
        List<Lender> returnedLenders = lenderService.getLenders();
        assertTrue(returnedLenders.get(0).getName().equals(allLenders.get(0).getName()));
        assertTrue(returnedLenders.get(0).getRate().equals(allLenders.get(0).getRate()));
        assertTrue(returnedLenders.get(0).getAvailableAmount().equals(allLenders.get(0).getAvailableAmount()));
        assertTrue(returnedLenders.get(1).getName().equals(allLenders.get(1).getName()));
        assertTrue(returnedLenders.get(1).getRate().equals(allLenders.get(1).getRate()));
        assertTrue(returnedLenders.get(1).getAvailableAmount().equals(allLenders.get(1).getAvailableAmount()));
        assertTrue(returnedLenders.get(2).getName().equals(allLenders.get(2).getName()));
        assertTrue(returnedLenders.get(2).getRate().equals(allLenders.get(2).getRate()));
        assertTrue(returnedLenders.get(2).getAvailableAmount().equals(allLenders.get(2).getAvailableAmount()));
        assertTrue(returnedLenders.get(3).getName().equals(allLenders.get(3).getName()));
        assertTrue(returnedLenders.get(3).getRate().equals(allLenders.get(3).getRate()));
        assertTrue(returnedLenders.get(3).getAvailableAmount().equals(allLenders.get(3).getAvailableAmount()));
        assertTrue(returnedLenders.get(4).getName().equals(allLenders.get(4).getName()));
        assertTrue(returnedLenders.get(4).getRate().equals(allLenders.get(4).getRate()));
        assertTrue(returnedLenders.get(4).getAvailableAmount().equals(allLenders.get(4).getAvailableAmount()));
        assertTrue(returnedLenders.get(5).getName().equals(allLenders.get(5).getName()));
        assertTrue(returnedLenders.get(5).getRate().equals(allLenders.get(5).getRate()));
        assertTrue(returnedLenders.get(5).getAvailableAmount().equals(allLenders.get(5).getAvailableAmount()));
        assertTrue(returnedLenders.get(6).getName().equals(allLenders.get(6).getName()));
        assertTrue(returnedLenders.get(6).getRate().equals(allLenders.get(6).getRate()));
        assertTrue(returnedLenders.get(6).getAvailableAmount().equals(allLenders.get(6).getAvailableAmount()));
    }

    @Test
    public void getPossibleMaxLenderOffer_marketDataTestGiven_ShouldReturnExpectedAmount() {
        assertEquals(2330, lenderService.getPossibleMaxLenderOffer());
    }
}
