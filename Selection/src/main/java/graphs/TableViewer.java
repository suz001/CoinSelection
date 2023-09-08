package graphs;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import setupandUI.MainUI;
import strategy.StrategyOutputObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * TableViewer.java
 * Build the bable of the trade action
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 *
 */

public class TableViewer implements ObserverInterface {
    private TradeList tradeLog; 

    @Override
    /**
     * call the function createTableOutput() to create the table
     */
    public void update() {
        createTableOutput();
    }
    
    /**
     * get the information from tradeLog
     * @param tradeLog the log includes the trade information
     */ 
    public TableViewer(TradeList tradeLog){
        this.tradeLog = tradeLog;
    }

    private void createTableOutput() {
        Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};

        Object[][] data = toArray();

        JTable table = new JTable(data, columnNames);
       
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);

        MainUI.getInstance().updateStats(scrollPane);
    }
    
    /**
     * put the trade info into a 2D array
     * @return a 2D array stores the information needed for constructing trade table
     */
    private Object[][] toArray(){
        Object[][] realData = new Object[tradeLog.getTradeLog().size()][7];
        ArrayList<StrategyOutputObject> trades = tradeLog.getTradeLog();
        for (int row =0; row<trades.size(); row++){
            for (int column = 0; column < 7; column++){
                switch (column){
                    case 0: {
                        realData[row][column] = trades.get(row).getTradingBroker().getName();
                        break;
                    }
                    case 1: {
                        realData[row][column] = trades.get(row).getStrategy().getName();
                        break;
                    }
                    case 2:{
                        realData[row][column]=trades.get(row).getCoin();
                        break;
                    }
                    case 3:
                    {
                        realData[row][column]=trades.get(row).getBuySell();
                        break;
                    }
                    case 4:{
                        if(trades.get(row).getQuantity() == -1){
                            realData[row][column]= "null";
                        } else{
                            realData[row][column]=trades.get(row).getQuantity();
                        }
                        break;
                    }
                    case 5:{
                        if(trades.get(row).getPrice() == -1.0){
                            realData[row][column]= "null";
                        } else {
                            realData[row][column] = trades.get(row).getPrice();
                        }
                        break;
                    }
                    case 6:{
                        realData[row][column]=trades.get(row).getDate();
                        break;
                    }

                }
            }
        }
    return realData;
    }


}
