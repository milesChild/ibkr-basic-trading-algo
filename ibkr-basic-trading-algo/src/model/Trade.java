package model;

public class Trade {
    private int orderID;
    private String ticker;
    private double executionPrice;
    int orderQuantity;

    public Trade(int orderID, String ticker, double executionPrice, int orderQuantity) {
        this.orderID = orderID;
        this.ticker = ticker;
        this.executionPrice = executionPrice;
        this.orderQuantity = orderQuantity;
    }

}
