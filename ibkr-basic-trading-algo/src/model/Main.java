package model;

import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.client.OrderCondition;
import strategies.PnLTrader;
import strategies.Strategy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static ArrayList<Contract> contracts = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        double acctBalance = 1000.0;
        double positionSize = 500.0;
        OrderManager manager = new OrderManager();
        Strategy strategy = new PnLTrader();
        initContracts();

        // begin trading process
        System.out.println("---------- Trading ----------");
        Thread.sleep(500);

        // First, send all open orders

        if (acctBalance > positionSize) {
            for (Contract c : contracts) {
                // update contract details
                c.conid(manager.getContractId(4444, c));
                // create conditions for execution & create an order object and append the conditions
                Order order = strategy.generateOpenOrder(c);
                // send the order off to the IBKR server
                manager.sendMarketOrder(order, c);
            }
        }

        Contract tempC = new Contract();
        tempC.symbol("AAPL");
        tempC.secType("STK");
        tempC.exchange("SMART");

        Order tempO = new Order();
        tempO.orderId(///);
        tempO.action("BUY");
        tempO.totalQuantity(10);
        tempO.orderType("MKT");

        manager.sendMarketOrder(tempO, tempC);

    }

    public static void mainT(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
        double acctBalance = 1000.0;
        double positionSize = 500.0;
        HashMap<Integer, Trade> positions = new HashMap<>();
        OrderManager manager = new OrderManager();
        Strategy strategy = new PnLTrader();
        int reqId = 0;
        initContracts();

        // begin trading process
        System.out.println("---------- Trading ----------");
        Thread.sleep(500);

        // Stop trading as soon as the number of open positions is equal to 0 AND the account balance is lower or equal
        // to the desired position size.
        while (!(positions.size() == 0) && (acctBalance <= positionSize)) {

//            // First, monitor the open positions to see if any should be closed
//            for (Trade t : positions.values()) {
//                if (strategy.shouldClose(t)) {
//
//                    /*
//                     Order execution will take place within main. In the future, it may be better design to have
//                     an execute method in the strategies themselves and pass the order. For example, if the
//                     particular strategy is sell-normal (requires a sale to initiate a position) then this system
//                     would not support such a strategy.
//                     */
//                    manager.sendMarketOrder(t.getContract(), "SELL");
//                    positions.remove(t);
//                }
//            }

            // search for more trades
            if (acctBalance > positionSize) {
                for (Contract c : contracts) {
                    // update contract details
                    c.conid(manager.getContractId(reqId, c));
                    reqId++;
                    // create conditions for execution & create an order object and append the conditions
                    Order order = strategy.generateOpenOrder(c);
                    // send the order off to the IBKR server
                    manager.sendMarketOrder(order, c);
                }
            }
        }

        System.out.println("---------- Finished Trading ----------");
        Thread.sleep(500);
        System.out.println("---------- System Off ----------");
    }

//    private void closeOrders() {
//        for (Contract c : positions) {
//            // obtain position details
//            // create an order object and append the conditions for closing based on the strategy
//            Order order = strategy.generateCloseOrder(c);
//            // send the order off to the IBKR server
//            manager.sendMarketOrder(order, c);
//        }
//    }

    public static void initContracts() {
        Contract AAPL = new Contract();
        AAPL.symbol("AAPL");
        AAPL.secType("STK");
        AAPL.exchange("SMART");
        AAPL.currency("USD");
        AAPL.primaryExch("NASDAQ");
        contracts.add(AAPL);

//        Contract TSLA = new Contract();
//        TSLA.symbol("TSLA");
//        TSLA.secType("STK");
//        TSLA.exchange("SMART");
//        TSLA.currency("USD");
//        TSLA.primaryExch("NASDAQ");
//        contracts.add(TSLA);
//
//        Contract NVDA = new Contract();
//        NVDA.symbol("NVDA");
//        NVDA.secType("STK");
//        NVDA.exchange("SMART");
//        NVDA.currency("USD");
//        NVDA.primaryExch("NASDAQ");
//        contracts.add(NVDA);
    }

}
