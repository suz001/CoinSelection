package strategy;
import utils.TradingBroker;
import utils.CoinsDatabase;

/**
 * Strategy.java
 * Strategy interface that is used for the Factory design pattern
 * this interface is used by the all Strategy classes
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public interface StrategyInterface {

    String getName();
    StrategyOutputObject buyOrSell(String[] coinList, CoinsDatabase cDataBase, TradingBroker Tbroker);

}
