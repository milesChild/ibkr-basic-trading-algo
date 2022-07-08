package strategies;

import com.ib.client.Order;
import model.OrderManager;
import model.Trade;
import com.ib.client.Contract;

public class RandomTrader implements Strategy {

                // Makes ten random trades in a span of 5 seconds
//        for (int i = 0; i < 10; i++) {
//            Random r = new Random();
//            int t = r.nextInt(5);
//            String type;
//            if (t % 2 == 0) {
//                type = "BUY";
//            } else {
//                type = "SELL";
//            }
//            String ticker = tickers.get(t);
//            order.sendMarketOrder(ticker, type);
//            Thread.sleep(5000);
//        }

    @Override
    public Order generateOpenOrder(Contract contract) {
        return null;
    }

    @Override
    public Order generateCloseOrder(Contract contract) {
        return null;
    }
}
