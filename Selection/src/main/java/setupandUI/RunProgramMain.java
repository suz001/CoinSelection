package setupandUI;
import graphs.HistogramViewer;
import graphs.TableViewer;
import graphs.TradeList;
import utils.TradingBroker;
import utils.TBrokerFactory;
import utils.TBrokerList;
import utils.UserSelection;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class RunProgramMain {

    static TBrokerList brokerList = new TBrokerList();
    static TradeList tList= new TradeList();

    static HistogramViewer histogram = new HistogramViewer(tList);
    static TableViewer table = new TableViewer(tList);
    
    /**
     * the main class for running program
     * @param args
     */
    public static void main(String[] args) {

        LoginUI.launchLogInUI();
        attachObservers();
    }
    
    /**
     * if username and password are correct, launch the mainUI; else exit
     * @param valid the validation of input username and password
     */
    public static void loginCheck(Boolean valid){
        if(valid){
            MainUI.launch();
        }
        else{
            System.exit(0);
        }
    }
    
    /**
     * get the input broker name, coin list and strategy from user
     * @param name the input broker name
     * @param coins the input coins list
     * @param strategy the strategy user selected
     */
    public static void addUserSelection(String name, String[] coins, String strategy){
        UserSelection newSelection = new UserSelection(name,coins,strategy);
        TBrokerFactory TbrokerFactory = new TBrokerFactory();
        TradingBroker newBroker = TbrokerFactory.create(newSelection);
        brokerList.addTradingBroker(newBroker);
    }

    public static void invokeStrategies(){
        brokerList.trade(tList);
    }
    
    /**
     * attach the (observers) histogram and table to the trade log
     */
    public static void attachObservers(){
    	tList.attach(histogram);
    	tList.attach(table);
    }

    public static void clearAll(){
        brokerList.clearAll();
    }
}
