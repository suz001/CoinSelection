package utils;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class Coin {

    private String ticker;
    private String fullName;
    private double price;
    
    //constructor
    public Coin(String ticker, String fullName, double price){
        this.fullName = fullName;
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker(){
        return ticker;
    }

    public String getFullName(){
        return fullName;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public String toString(){
        return ("Full Name: " + fullName + "\nTicker: " + ticker + "\nPrice: " + price + "\n");
    }

}
