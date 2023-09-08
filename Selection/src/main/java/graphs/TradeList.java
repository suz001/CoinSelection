package graphs;
import java.util.ArrayList;

/*TradeLog = Observer design pattern
* */

import strategy.StrategyOutputObject;

/**
 * TradeLog.java
 * Receive the trader information needed for constructing tables and histogram
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 */

public class TradeList {

    private ArrayList<ObserverInterface> observers = new ArrayList<>();
    private ArrayList<StrategyOutputObject> trades = new ArrayList<>();

    /**
     * get all trades
     * @return a list a trades needed
     */
    
    public ArrayList<StrategyOutputObject> getTradeLog() {
        return trades;
    }
    
    /**
     * Get each trade
     * @param broker the broker's name of the broker
     * @param date the date of the trade date
     * @return null
     */
    
    public StrategyOutputObject getTrade(String broker, String date){

        for (StrategyOutputObject trade: trades){
            if (trade.getDate().equals(date) && trade.getTradingBroker().getName().equals(broker))return trade;
        }
        return null;
    }
    
    /**
     * notify the observer to update the trade's information
     */
    
    public void notifyObservers(){
        for (ObserverInterface o : observers){
            o.update();
        }
    }
    
    /**
     * Add the trade to the log
     * @param s is the strategy analysis result 
     * @return true if successful add the trade
     */
    
    public boolean addTrade(StrategyOutputObject s){
        return trades.add(s);
    }
    
    /**
     * add the trade to the observer
     * @param observer the observer need to be added to the observer list
     */
    
    public void attach(ObserverInterface observer){
        observers.add(observer);
    }

}
