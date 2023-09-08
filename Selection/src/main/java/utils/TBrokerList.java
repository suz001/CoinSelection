package utils;
import java.util.ArrayList;

import graphs.TradeList;
import strategy.StrategyOutputObject;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class TBrokerList{

    private ArrayList<TradingBroker> brokerArrayList = new ArrayList<TradingBroker>();
    private String[] fullCoinList;
    private CoinsDatabase coinDataBase = new CoinsDatabase();

    public ArrayList<TradingBroker> getBrokerArrayList() {
        return this.brokerArrayList;
    }
    
    /**
     * get the broker with the input name
     * @param brokerName
     * @return
     */
    public TradingBroker getTradingBroker(String brokerName) {
        for (int i = 0; i < brokerArrayList.size(); i ++){
            if (brokerArrayList.get(i).getName().equals(brokerName)){
                return brokerArrayList.get(i);
            }
        }
        return null;
    }
    
    /**
     * remove the broker
     * @param b is the broker need to be removed
     * @return true if it is sucessful, false otherwise
     */
    public boolean removeTradingBroker(TradingBroker b) {
        boolean retValue = this.brokerArrayList.remove(b);
        upAllCoinsList();
        return retValue;
    }
    
    /**
     * add the given broker to the broker list
     * @param b is the given broker
     * @return true if broker is added, false otherwise
     */
    public boolean addTradingBroker(TradingBroker b) {
        boolean retValue = this.brokerArrayList.add(b);
        upAllCoinsList();
        return retValue;
    }

    private void upAllCoinsList(){
        ArrayList<String> startList = new ArrayList<>(); 
        for (int i = 0; i < brokerArrayList.size(); i ++){ 
            for (int j = 0; j < brokerArrayList.get(i).getCryptoList().length; j ++){ 
                if (!startList.contains(brokerArrayList.get(i).getCryptoList()[j])){ 
                    startList.add(brokerArrayList.get(i).getCryptoList()[j]); 
                }
            }
        }
        this.fullCoinList = new String[startList.size()];
        for (int i = 0; i < fullCoinList.length; i ++){
            fullCoinList[i] = startList.get(i);
        }
    }
    /**
     * Calls each broker to perform trade actions
     * @param tList
     */
    public void trade(TradeList tList){

        coinDataBase.updateInfo(fullCoinList);

        for (int i = 0; i < brokerArrayList.size(); i ++){
            StrategyOutputObject result = brokerArrayList.get(i).determineTrade(coinDataBase);
            tList.addTrade(result);
        }
        tList.notifyObservers();
    }
    
    public void clearAll(){
        this.brokerArrayList.clear();
    }
}
