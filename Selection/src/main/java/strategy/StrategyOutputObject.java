package strategy;
import java.time.LocalDate;

import utils.TradingBroker;
/**
 * Construct the output of the strategy's decisions
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
*/
public class StrategyOutputObject {
    private int quantity;
    private String coin;
    
    private String buysell;
    private LocalDate date;
    private double price;
    private TradingBroker broker;
    private StrategyInterface strategy;

    public StrategyOutputObject(String coin, int quantity, String buysell, LocalDate date, double price, TradingBroker broker, StrategyInterface strategy) {
        this.coin = coin;
    	this.quantity = quantity;
        this.buysell = buysell;
        this.date = date;
        this.price = price;
        this.broker = broker;
        this.strategy = strategy;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBuySell() {
        return buysell;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TradingBroker getTradingBroker() {
        return broker;
    }

    public void setBroker(TradingBroker Tbroker) {
        this.broker = Tbroker;
    }

    public StrategyInterface getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyInterface strategy) {
        this.strategy = strategy;
    }
}
