package strategy;

/**
 * StrategyDayTrending.java
 * Implement the Strategy interface
 * Analyse the DayTrending strategy
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
import java.time.LocalDate;

import setupandUI.MainUI;
import utils.TradingBroker;
import utils.CoinsDatabase;

public class StrategyDayTrending implements StrategyInterface {

    private final String stratName = "DAYTRENDING";
    private final String coin1 = "ethereum";
    private final String coin2 = "tether";

    @Override
    public String getName() {
        return stratName;
    }

    @Override
    /**
     * get the strategy result analysis
     * @param coinList applied to the strategy
     * @param coinDataBase the coin prices needed for the strategy
     * @param broker the broker who decides to use this strategy
     * @return testResult gives all information needs for the trade table: quantity, coin, action
     * date,price, broker and the strategy 
     */
    public StrategyOutputObject buyOrSell(String[] coinList, CoinsDatabase coinDataBase, TradingBroker broker) {


        if (!ValidateCoins(coinList)) {
            MainUI.incorrectCoin(broker);
            return new StrategyOutputObject(null ,-1,"Fail",java.time.LocalDate.now(),-1,broker, broker.getStrategy());
        }
        // if ethereum price >  tether price *2 sell 300 ethereum coins
        if (coinDataBase.getInfo(coinList[0]).getPrice() > coinDataBase.getInfo(coin2).getPrice() * 2){
            StrategyOutputObject testResult = new StrategyOutputObject(coin1, 300,"Sell", LocalDate.now(),
                    coinDataBase.getInfo(coin1).getPrice(),
                    broker, this);

            return testResult;
            // else buy 500 tether
        } else {
            StrategyOutputObject testResult = new StrategyOutputObject(coin2, 500,"Buy", LocalDate.now(),
                    coinDataBase.getInfo(coin1).getPrice(),
                    broker, this);
            return testResult;
        }

    }
    
    /**
     * check if the input coins is valid for applying the strategy
     * @param coinList the input coin list
     * @return return true if the input is valid, false if not
     */
    private boolean ValidateCoins(String[] coinList){
        boolean foundCoin1 = false;
        for (int i = 0; i < coinList.length; i ++){
            if (coin1.equals(coinList[i])){
                foundCoin1 = true;
            }
        }
        if (!foundCoin1){
            return false;
        }
        boolean foundCoin2 = false;
        for (int i = 0; i < coinList.length; i ++){
            if (coin2.equals(coinList[i])){
                foundCoin2 = true;
            }
        }
        return foundCoin2;
    }
}
