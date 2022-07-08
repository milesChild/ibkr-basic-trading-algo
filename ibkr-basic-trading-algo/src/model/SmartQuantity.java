package model;

/**
 * Given a ticker and an order type, calculates the optimal quantity for purchase.
 */
public class SmartQuantity {
    private String ticker;
    private double acctBalance;
    private double positionSize;

    public SmartQuantity(String ticker, double acctBalance, double positionSize) {
        this.ticker = ticker;
        this.acctBalance = acctBalance;
        this.positionSize = positionSize;
    }

    /**
     * Calculates the optimal quantity to be purchased for the given stock based on the cost of the order and the
     * current balance.
     * @return an integer quantity to be purchased for the given order
     */
    public int calculate() {
        // TODO: Request stock price
        double last = 0;
        int qty = 0;
        double availableFunds = acctBalance;

        if (last > positionSize) {
            qty = 0;
        } else {
            qty = (int) Math.round(positionSize / last);
        }

        return qty;
    }

}
