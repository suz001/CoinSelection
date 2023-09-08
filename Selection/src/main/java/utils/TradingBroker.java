package utils;
import strategy.StrategyInterface;
import strategy.StrategyOutputObject;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class TradingBroker {
    private String name;
    private String[] cryptoTickerList;
    private StrategyInterface strategy;

    TradingBroker(String name, String[] cryptoTickerList, StrategyInterface strategy){
        setName(name);
        getCryptoList(cryptoTickerList);
        setStrategy(strategy);
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void getCryptoList(String[] cryptoTickerList) {
        this.cryptoTickerList = cryptoTickerList;
    }

    public void setStrategy(StrategyInterface strategy) {
        this.strategy = strategy;
    }

    public String[] getCryptoList(){
        return cryptoTickerList;
    }

    public StrategyInterface getStrategy(){
        return strategy;
    }

    public StrategyOutputObject determineTrade(CoinsDatabase dataBase){
        return strategy.buyOrSell(cryptoTickerList,dataBase,this);
    }

}
