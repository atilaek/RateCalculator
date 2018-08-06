package aek.demo.zopa.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import aek.demo.zopa.exceptions.InsufficientOfferEception;
import aek.demo.zopa.model.Lender;

public class CalculationServiceTest {
    @Mock
    private LenderService lenderServiceMock;

    @InjectMocks
    private CalculationService calculationServiceTest;

    private List<Lender> allLenders = new ArrayList<>();
    private List<Lender> requiredLenders = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        lenderServiceMock = mock(LenderService.class);

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

        when(lenderServiceMock.getPossibleMaxLenderOffer()).thenReturn(2330);
        when(lenderServiceMock.getLenders()).thenReturn(allLenders);
    }

    @Test
    public void findRequiredLendersWithAmounts_ShouldReturnExpectedRequiredLenderes()
            throws InsufficientOfferEception {
        final List<Lender> returnedLenders = calculationServiceTest.
                findRequiredLendersWithAmounts(1100, lenderServiceMock);

        assertTrue(returnedLenders.get(0).getName().equals(requiredLenders.get(0).getName()));
        assertTrue(returnedLenders.get(0).getRate().equals(requiredLenders.get(0).getRate()));
        assertTrue(returnedLenders.get(0).getAvailableAmount().equals(requiredLenders.get(0).getAvailableAmount()));
        assertTrue(returnedLenders.get(1).getName().equals(requiredLenders.get(1).getName()));
        assertTrue(returnedLenders.get(1).getRate().equals(requiredLenders.get(1).getRate()));
        assertTrue(returnedLenders.get(1).getAvailableAmount().equals(requiredLenders.get(1).getAvailableAmount()));
        assertTrue(returnedLenders.get(2).getName().equals(requiredLenders.get(2).getName()));
        assertTrue(returnedLenders.get(2).getRate().equals(requiredLenders.get(2).getRate()));
        assertTrue(returnedLenders.get(2).getAvailableAmount().equals(requiredLenders.get(2).getAvailableAmount()));
        assertTrue(returnedLenders.get(3).getName().equals(requiredLenders.get(3).getName()));
        assertTrue(returnedLenders.get(3).getRate().equals(requiredLenders.get(3).getRate()));
        assertTrue(returnedLenders.get(3).getAvailableAmount().equals(requiredLenders.get(3).getAvailableAmount()));
    }

    @Test(expected = InsufficientOfferEception.class)
    public void findRequiredLendersWithAmounts_RequestedAmountIsMoreThanPossibleMaxLenderOffer_ThrowsErrorMessage()
            throws InsufficientOfferEception {
        when(lenderServiceMock.getPossibleMaxLenderOffer()).thenReturn(1099);
        calculationServiceTest.findRequiredLendersWithAmounts(1100, lenderServiceMock);
    }

    @Test
    public void calculateAverageRate_ShouldReturnExpectedAverageRate() {
        assertEquals(new BigDecimal(7.02400).setScale(5, BigDecimal.ROUND_HALF_UP),
                     calculationServiceTest.calculateAverageRate(new BigDecimal(1177.26),
                                                                 new BigDecimal(1100)));
    }

    @Test
    public void calculateTotalRepayment_ShouldReturnExpectedTotalRepayment() {
        assertEquals(new BigDecimal(1177.260).setScale(3, BigDecimal.ROUND_HALF_UP),
                     calculationServiceTest.calculateTotalRepayment(requiredLenders));
    }

    @Test
    public void calculateMonthlyRepayment_ShouldReturnExpectedMonthlyRepayment() {
        assertEquals(new BigDecimal(32.70167).setScale(5, BigDecimal.ROUND_HALF_UP),
                     calculationServiceTest.calculateMonthlyRepayment(new BigDecimal(1177.260).
                                                                              setScale(3,
                                                                                       BigDecimal.ROUND_HALF_UP),
                                                                      new BigDecimal(36).
                                                                              setScale(0,
                                                                                       BigDecimal.ROUND_HALF_UP)
                     )
        );
    }
}
