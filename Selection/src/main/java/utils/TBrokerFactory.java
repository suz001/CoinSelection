package utils;
import strategy.StrategyInterface;
import strategy.StrategyFactory;
/**
 * BrokerFactory.java
 * @purpose implements the factory for broker
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public class TBrokerFactory {
    public TradingBroker create(UserSelection usrSelection){// factory for creating the broker
        return new TradingBroker(
                retrieveName(usrSelection),
                retrieveTckrLst(usrSelection),
                retrieveStrategy(usrSelection));
    }

    private String retrieveName(UserSelection usrSelection){
        return usrSelection.getName();
    }

    private String[] retrieveTckrLst(UserSelection usrSelection){

        return usrSelection.getCoinList();
    }

    private StrategyInterface retrieveStrategy(UserSelection usrSelection){
        StrategyFactory factory = new StrategyFactory();
        return factory.create(usrSelection.getStrategy());
    }
}
