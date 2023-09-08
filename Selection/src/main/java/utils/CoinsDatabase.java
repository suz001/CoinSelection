package utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * CoinsCollection.java
 * @purpose Coin database for storing and providing information on coins
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public class CoinsDatabase {

    private HashMap<String, Coin> coinMap;

    public CoinsDatabase(){
    	coinMap = new HashMap<String, Coin>();
    }
    
    /**
     * get the coin with the given coinName
     * @param coinName
     * @return coin with given coinName
     */
    public Coin getInfo(String coinName){
        return coinMap.get(coinName);
    }
    
    /**
     * check if we can find the coin from the website
     * @param coin
     * @return true if we did, false otherwise
     */
    public boolean checkValidCoin(String coin){
        return CoinGeckoAPI.checkCoin(coin);
    }
    
    /**
     * update the needed coin information
     * @param names
     * @return
     */
    public boolean updateInfo(String[] names){

        ArrayList<String> holder = new ArrayList<>(Arrays.asList(names));

        for (int i = 0; i < holder.size(); i ++){
            if (!checkValidCoin(holder.get(i))){
                holder.remove(i);
            }
        }

        String[] newList = new String[holder.size()];
        newList = holder.toArray(newList);

        try {
        	coinMap = CoinGeckoAPI.updateCoinHash(newList, "usd", coinMap);
        } catch (Exception infoException){
            System.out.println(infoException);
            return false;
        }

        return true;

    }
}
