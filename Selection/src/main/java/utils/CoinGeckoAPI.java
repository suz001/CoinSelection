package utils;
/**
 * @purpose get information needed from CoinGecko and help merge it with the rest of backend
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CoinGeckoAPI {
	/**
	 * 
	 * @return true if a coin exists on coinGecko
	 */
    public static boolean checkCoin(String coinName){
        try {
            URL url = new URL
                    ("https://api.coingecko.com/api/v3/simple/price?ids=" + coinName
                            + "&vs_currencies=usd");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            String data = "";
            Scanner scan = new Scanner(url.openStream());

            while (scan.hasNext()) {
            	data = data + scan.nextLine();
            }
            scan.close();
            
            if (data.equals("{}")){
                return false;
            }
            
            return true;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    /**
     * converts the clist into a string with ",", uses that to update prices of coins
     */
    public static HashMap<String, Coin> updateCoinHash(String[] cList, String currencyType, HashMap<String, Coin> curMap) throws IOException, ParseException {
            String callString = "";
            for (int i = 0; i < cList.length - 1; i++) {
                callString += cList[i] + ",";
            }
            callString += cList[cList.length - 1];
            URL url = null;
            
			try {
				url = new URL
				        ("https://api.coingecko.com/api/v3/simple/price?ids=" + callString
				                + "&vs_currencies=" + currencyType);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
				connection.setRequestMethod("GET");
	            connection.connect();
			} catch (ProtocolException e) {
				e.printStackTrace();
			}


            String data = "";
            Scanner scan = new Scanner(url.openStream());

            while (scan.hasNext()) {
            	data = data + scan.nextLine();
            }
            scan.close();

            JSONParser parser = new JSONParser();
            JSONObject apiReturnJSON = (JSONObject) parser.parse(data);

            for (int i = 0; i < cList.length; i++) {

                JSONObject coinInfo = (JSONObject) apiReturnJSON.get(cList[i]);

                if (curMap.containsKey(cList[i])) {
                    curMap.get(cList[i]).setPrice(((Number)(coinInfo.get(currencyType))).doubleValue());
                } else {
                    Coin newCoin = new Coin("placeholder", cList[i],
                             ((Number)(coinInfo.get(currencyType))).doubleValue());
                    curMap.put(cList[i], newCoin);
                }
            }
        return curMap;
    }
}
