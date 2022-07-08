package model;

import com.ib.client.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Creates the socket client to connect to the TWS, the stock object, and initializes order attributes.
public class OrderManager extends Thread implements EWrapper {
    private EClientSocket clientSocket;
    private EReaderSignal readerSignal;
    private Order order;
    private int orderId;
    private ArrayList<Contract> contracts;

    // Constructor connects to TWS
    public OrderManager() {
        System.out.println("---------- Attempting to Connect to TWS ----------");
        this.readerSignal = new EJavaSignal();
        this.clientSocket = new EClientSocket(this, readerSignal);
        clientSocket.eConnect("127.0.0.1", 7497, 2);
        this.order = new Order();

        try {
            Thread.sleep(5000);
            System.out.println("---------- Connecting... ----------");
            while (!(clientSocket.isConnected())) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------- Connected Successfully ----------");
        this.orderId = 0;
        this.contracts = new ArrayList<>();
    }

    // method to actually send an order to TWS
//    public void sendMarketOrder(Contract contract, String orderType, int qty) throws SQLException {
//        this.nextValidId(orderId);
//        order.action(orderType);
//        order.orderId(this.orderId);
//        order.orderType("MKT");
//        order.totalQuantity(qty);
//        order.account("DU5733587");
//        order.clientId(1);
//
//        clientSocket.placeOrder(orderId, contract, order);
//    }

    // method to actually send an order to TWS
    public void sendMarketOrder(Order order, Contract contract) {
        this.nextValidId(orderId);
        order.account("DU5733587");
        order.clientId(2);

        clientSocket.placeOrder(order.orderId(), contract, order);

    }

    // gets the contract id of the given contract
    public int getContractId(int reqId, Contract c) {
        // TODO: make an error checking system
        this.clientSocket.reqContractDetails(reqId, c);
        //return this.contracts.get(contracts.size() - 1).conid();
        return 6;
    }

    // vvv methods for getting market data vvv

//    public double getLast(Contract c) {
//        this.clientSocket.reqMktData(1, c, "Last", true, false, new ArrayList<TagValue>());
//    }

    // vvv IBKR callbacks vvv

    @Override
    public void tickPrice(int tickerId, int field, double price, TickAttrib attrib) {

    }

    @Override
    public void tickSize(int tickerId, int field, int size) {

    }

    @Override
    public void tickOptionComputation(int tickerId, int field, int tickAttrib, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {

    }

    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {

    }

    @Override
    public void tickString(int tickerId, int tickType, String value) {

    }

    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureLastTradeDate, double dividendImpact, double dividendsToLastTradeDate) {

    }

    @Override
    public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
        System.out.println("Order Status --- orderid:" + orderId + " | status:" + status + " | filled" + filled +
                " | remaining" + remaining + " | lastFillPrice" + lastFillPrice + " ---");
    }

    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
        System.out.println("Order Opened --- orderid:" + orderId + " | symbol:" + contract.symbol() +
                " | order action:" + order.action() + " | order type:" + order.getOrderType() +
                " | order quantity:" + order.totalQuantity() + " ---");
    }

    @Override
    public void openOrderEnd() {

    }

    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {

    }

    @Override
    public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {

    }

    @Override
    public void updateAccountTime(String timeStamp) {

    }

    @Override
    public void accountDownloadEnd(String accountName) {

    }

    @Override
    public void nextValidId(int orderId) {

    }

    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails) {
        System.out.println("Contract ID:" + contractDetails.contract().conid());
        this.contracts.add(contractDetails.contract());
    }

    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {

    }

    @Override
    public void contractDetailsEnd(int reqId) {
        System.out.println("contractDetailsEnded");
    }

    @Override
    public void execDetails(int reqId, Contract contract, Execution execution) {
        System.out.println("Order Executed --- reqid:" + reqId + " | symbol:" + contract.symbol() +
                " | quantity:" + order.totalQuantity() +
                " @ " + execution.avgPrice() +
                " ---");
    }

    @Override
    public void execDetailsEnd(int reqId) {

    }

    @Override
    public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {

    }

    @Override
    public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size, boolean isSmartDepth) {

    }

    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {

    }

    @Override
    public void managedAccounts(String accountsList) {

    }

    @Override
    public void receiveFA(int faDataType, String xml) {

    }

    @Override
    public void historicalData(int reqId, Bar bar) {

    }

    @Override
    public void scannerParameters(String xml) {

    }

    @Override
    public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr) {

    }

    @Override
    public void scannerDataEnd(int reqId) {

    }

    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {

    }

    @Override
    public void currentTime(long time) {

    }

    @Override
    public void fundamentalData(int reqId, String data) {

    }

    @Override
    public void deltaNeutralValidation(int reqId, DeltaNeutralContract deltaNeutralContract) {

    }

    @Override
    public void tickSnapshotEnd(int reqId) {

    }

    @Override
    public void marketDataType(int reqId, int marketDataType) {

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {

    }

    @Override
    public void position(String account, Contract contract, double pos, double avgCost) {

    }

    @Override
    public void positionEnd() {

    }

    @Override
    public void accountSummary(int reqId, String account, String tag, String value, String currency) {

    }

    @Override
    public void accountSummaryEnd(int reqId) {

    }

    @Override
    public void verifyMessageAPI(String apiData) {

    }

    @Override
    public void verifyCompleted(boolean isSuccessful, String errorText) {

    }

    @Override
    public void verifyAndAuthMessageAPI(String apiData, String xyzChallenge) {

    }

    @Override
    public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {

    }

    @Override
    public void displayGroupList(int reqId, String groups) {

    }

    @Override
    public void displayGroupUpdated(int reqId, String contractInfo) {

    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void error(String str) {

    }

    @Override
    public void error(int id, int errorCode, String errorMsg) {

    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectAck() {

    }

    @Override
    public void positionMulti(int reqId, String account, String modelCode, Contract contract, double pos, double avgCost) {

    }

    @Override
    public void positionMultiEnd(int reqId) {

    }

    @Override
    public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value, String currency) {

    }

    @Override
    public void accountUpdateMultiEnd(int reqId) {

    }

    @Override
    public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId, String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {

    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int reqId) {

    }

    @Override
    public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {

    }

    @Override
    public void familyCodes(FamilyCode[] familyCodes) {

    }

    @Override
    public void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {

    }

    @Override
    public void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {

    }

    @Override
    public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {

    }

    @Override
    public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline, String extraData) {

    }

    @Override
    public void smartComponents(int reqId, Map<Integer, Map.Entry<String, Character>> theMap) {

    }

    @Override
    public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {

    }

    @Override
    public void newsProviders(NewsProvider[] newsProviders) {

    }

    @Override
    public void newsArticle(int requestId, int articleType, String articleText) {

    }

    @Override
    public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {

    }

    @Override
    public void historicalNewsEnd(int requestId, boolean hasMore) {

    }

    @Override
    public void headTimestamp(int reqId, String headTimestamp) {

    }

    @Override
    public void histogramData(int reqId, List<HistogramEntry> items) {

    }

    @Override
    public void historicalDataUpdate(int reqId, Bar bar) {

    }

    @Override
    public void rerouteMktDataReq(int reqId, int conId, String exchange) {

    }

    @Override
    public void rerouteMktDepthReq(int reqId, int conId, String exchange) {

    }

    @Override
    public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {

    }

    @Override
    public void pnl(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL) {

    }

    @Override
    public void pnlSingle(int reqId, int pos, double dailyPnL, double unrealizedPnL, double realizedPnL, double value) {

    }

    @Override
    public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {

    }

    @Override
    public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {

    }

    @Override
    public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {

    }

    @Override
    public void tickByTickAllLast(int reqId, int tickType, long time, double price, int size, TickAttribLast tickAttribLast, String exchange, String specialConditions) {

    }

    @Override
    public void tickByTickBidAsk(int reqId, long time, double bidPrice, double askPrice, int bidSize, int askSize, TickAttribBidAsk tickAttribBidAsk) {

    }

    @Override
    public void tickByTickMidPoint(int reqId, long time, double midPoint) {

    }

    @Override
    public void orderBound(long orderId, int apiClientId, int apiOrderId) {

    }

    @Override
    public void completedOrder(Contract contract, Order order, OrderState orderState) {
        // TODO: Add an order to a list of positions, eliminate need for trade class

    }

    @Override
    public void completedOrdersEnd() {

    }

    @Override
    public void replaceFAEnd(int reqId, String text) {

    }

}
