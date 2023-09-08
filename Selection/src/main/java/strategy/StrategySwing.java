package strategy;
import java.time.LocalDate;

import setupandUI.MainUI;
import utils.TradingBroker;
import utils.CoinsDatabase;
/**
 * PositionInvesting.java
 * Implement the Strategy interface
 * Analyse the Position strategy
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public class StrategySwing implements StrategyInterface{

    private final String stratName = "SWING";
    private final String coin1 = "tether";
    private final String coin2 = "gala";
    private final String coin3 = "bitcoin";
    @Override
    public String getName() {
        return stratName;
    }

    /**
     * get the strategy result analysis
     * @param coinList applied to the strategy
     * @param coinDataBase the coin prices needed for the strategy
     * @param broker the broker who decides to use this strategy
     * @return testResult gives all information needs for the trade table: quantity, coin, action
     * date,price, broker and the strategy 
     */
    public StrategyOutputObject buyOrSell(String[] coinList, CoinsDatabase coinDataBase, TradingBroker broker) {
        if (!validateCoins(coinList)) {
            MainUI.incorrectCoin(broker);
            return new StrategyOutputObject(null ,-1,"Fail",java.time.LocalDate.now(),-1,broker, broker.getStrategy());
        }
        // if tether > 500 and gala <40000 then sell 1500 bitcoins
        if (coinDataBase.getInfo(coin1).getPrice() > 500 && coinDataBase.getInfo(coin2).getPrice() < 40000){
            StrategyOutputObject testResult = new StrategyOutputObject(coin3, 1500,"Sell", LocalDate.now(),
                    coinDataBase.getInfo(coin3).getPrice(),
                    broker, this);

            return testResult;
            // else buy 10 gala
        } else {
            StrategyOutputObject testResult = new StrategyOutputObject(coin2, 10,"Buy", LocalDate.now(),
                    coinDataBase.getInfo(coin2).getPrice(),
                    broker, this);
            return testResult;
        }

    }
    
    /**
     * check if the input coins is valid for applying the strategy
     * @param coinList the input coin list
     * @return return true if the input is valid, false if not
     */
    private boolean validateCoins(String[] coinList){
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
        if(!foundCoin2)
        {
        	return false;
        }
        boolean foundCoin3 = false;
        for (int i = 0; i < coinList.length; i ++){
            if (coin3.equals(coinList[i])){
                foundCoin3 = true;
            }
        }
        return foundCoin3;
    }
}
