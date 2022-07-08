package strategies;

import com.ib.client.Order;
import model.OrderManager;
import com.ib.client.Contract;


public interface Strategy {

    Order generateOpenOrder(Contract contract);

    Order generateCloseOrder(Contract contract);

}
