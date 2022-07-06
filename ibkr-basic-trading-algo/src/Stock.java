import com.ib.client.Contract;

public class Stock {
    private int stockID;
    private String symbol;
    Contract contract = new Contract();

    // default constructor
    public Stock() {}

    // explicit constructor
    public Stock(int stockID, String symbol) {
        this.stockID = stockID;
        this.symbol = symbol;
    }

    // getters

    public int getStockId() {
        return this.stockID;
    }

    public String getSymbol() {
        return this.symbol;
    }

    // setters
    public void setStockID(int i) {
        this.stockID = i;
    }

    public void setSymbol(String s) {
        this.symbol = s;
    }

    public void createContract(String ticker) {
        contract.symbol(ticker);
        contract.exchange("SMART");
        contract.secType("STK");
        contract.currency("USD");
    }

}
