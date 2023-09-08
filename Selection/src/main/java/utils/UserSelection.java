package utils;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class UserSelection {

    private String name;
    private String[] cList;
    private String strategy;

    public UserSelection(String n, String[] coinList, String s){
        name = n;
        cList = coinList;
        strategy = s;
    }
    
    public String getName(){
        return name;
    }

    public String[] getCoinList(){
        return cList;
    }

    public String getStrategy() {
        return strategy;
    }
}
