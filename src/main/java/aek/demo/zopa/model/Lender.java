package aek.demo.zopa.model;

import java.math.BigDecimal;

/**
 * The class representing
 * a single lender who gives a loan.
 *
 * @author Atila Ekimci
 */
public class Lender implements Comparable<Lender> {

    private String name;
    private BigDecimal rate;
    private Integer availableAmount;

    /**
     * Main Lender constructor for creating Lender objects.
     *
     * @param name name of the Lender
     * @param rate interest rate of the Lender to give loan
     * @param availableAmount the amount that lender can give loan
     */
    public Lender(String name, BigDecimal rate, Integer availableAmount) {
        this.name = name;
        this.rate = rate;
        this.availableAmount = availableAmount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public int compareTo(final Lender o) {
        return this.rate.compareTo(o.rate);
    }
}
