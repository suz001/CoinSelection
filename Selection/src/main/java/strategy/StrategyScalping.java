package strategy;
/**
 * StrategyScalping.java
 * Implement the Strategy interface
 * Analyze the Scalping strategy
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
import java.time.LocalDate;

import setupandUI.MainUI;
import utils.TradingBroker;
import utils.CoinsDatabase;

public class StrategyScalping implements StrategyInterface{

    private final String stratName = "SCALPING";
    private final String coin1 = "bitcoin";
    private final String coin2 = "solana";
    private final String coin3 = "tether";

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

        if (!validateUserCoins(coinList)) {
            MainUI.incorrectCoin(broker);
            return new StrategyOutputObject(null ,-1,"Fail",java.time.LocalDate.now(),-1,broker, broker.getStrategy());
        }
        // if bitcoin/3 > 5800 and solana<bitcoin sell 1000 bitcoin
        if ((coinDataBase.getInfo(coin1).getPrice()/15 > 5800)&&
        		(coinDataBase.getInfo(coin2).getPrice()<coinDataBase.getInfo(coin1).getPrice())){
            StrategyOutputObject testResult = new StrategyOutputObject(coin1, 1000,"Sell", LocalDate.now(),
                    coinDataBase.getInfo(coin1).getPrice(),
                    broker, this);

            return testResult;
        } 
        // else if tether > solana*3 and techer> sell 2000  tether
        else if ((coinDataBase.getInfo(coin3).getPrice()> (coinDataBase.getInfo(coin2).getPrice()*3))&&
        		(coinDataBase.getInfo(coin3).getPrice()>coinDataBase.getInfo(coin1).getPrice()))
        {
            StrategyOutputObject testResult = new StrategyOutputObject(coin2, 2000,"Sell", LocalDate.now(),
                    coinDataBase.getInfo(coin3).getPrice(),
                    broker, this);
            return testResult;
        }
        //else buy 500 solana
        else  
        {
        	StrategyOutputObject testResult = new StrategyOutputObject(coin2, 500,"Buy", LocalDate.now(),
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
    private boolean validateUserCoins(String[] coinList){
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
