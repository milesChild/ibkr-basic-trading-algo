package strategies;

import com.ib.client.*;

public class PnLTrader implements Strategy {

    // opens an order with the condition that the price has crossed a certain level

    // Order conditioning: https://interactivebrokers.github.io/tws-api/order_conditions.html
    @Override
    public Order generateOpenOrder(Contract contract) {
        PriceCondition p = (PriceCondition)OrderCondition.create(OrderConditionType.Price);
        p.conId(contract.conid());
        p.exchange(contract.exchange());
        p.isMore(true);
        p.price(700);
        p.triggerMethod(Types.TriggerMethod.Last.val());

        Order order = new Order();
        order.action("BUY");
        order.totalQuantity(10);
        order.orderType("MKT");
        order.conditions().add(p);

        return order;
    }


    @Override
    public Order generateCloseOrder(Contract contract) {
        return null;
    }
}
