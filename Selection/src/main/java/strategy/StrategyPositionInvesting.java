package strategy;
/**
 * PositionInvesting.java
 * Implement the Strategy interface
 * Analyse the Position strategy
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
import java.time.LocalDate;

import setupandUI.MainUI;
import utils.TradingBroker;
import utils.CoinsDatabase;

public class StrategyPositionInvesting implements StrategyInterface{

    private final String stratName = "POSITION";
    private final String coin1 = "dash";
    private final String coin2 = "dogecoin";
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
    public StrategyOutputObject buyOrSell(String[] coinList, CoinsDatabase coinDataBase, TradingBroker Tbroker) {
    	
        if (!ValidateCoins(coinList)) {
            MainUI.incorrectCoin(Tbroker);
            return new StrategyOutputObject(null ,-1,"Fail",java.time.LocalDate.now(),-1,Tbroker, Tbroker.getStrategy());
        }
        // if the price of dash/15 > dogecoin sell 50 dash
        if (coinDataBase.getInfo(coin1).getPrice()/15 > coinDataBase.getInfo(coin2).getPrice()){
            StrategyOutputObject testResult = new StrategyOutputObject(coin1, 50,"Sell", LocalDate.now(),
                    coinDataBase.getInfo(coin1).getPrice(),
                    Tbroker, this);

            return testResult;
        }
        // else if the price of dogecoin/50 > dogecoin and dogecoin > 40 buy 20 dogecoins
        else if((coinDataBase.getInfo(coin1).getPrice()/50 > coinDataBase.getInfo(coin2).getPrice())&&
        		(coinDataBase.getInfo(coin2).getPrice()>40))
        {
            StrategyOutputObject testResult = new StrategyOutputObject(coin2, 20,"Buy", LocalDate.now(),
                    coinDataBase.getInfo(coin2).getPrice(),
                    Tbroker, this);
            return testResult;
        }
        //else just buy 70 dash
        else 
        {
        	StrategyOutputObject testResult = new StrategyOutputObject(coin1, 70,"Buy", LocalDate.now(),
                    coinDataBase.getInfo(coin1).getPrice(),
                    Tbroker, this);
            return testResult;
        }

    }
    
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


