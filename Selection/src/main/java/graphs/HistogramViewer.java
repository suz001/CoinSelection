package graphs;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import setupandUI.MainUI;
import strategy.StrategyOutputObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * HistogramViewer.java
 * Build the histogram
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public class HistogramViewer implements ObserverInterface {
    private TradeList tradeLog;
    
    /**
     * get the trade log
     * @param tradeLog
     */
    
    public HistogramViewer(TradeList tradeLog){
        this.tradeLog = tradeLog;
    }
    @Override
    /**
     * call the createBar() method to update the histogram
     */
    public void update() {
        createBar();
    }
    /**
     * create all the bar needed for the histogram and construct the histogram
     */
    private void createBar() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<StrategyOutputObject> trades = tradeLog.getTradeLog();

        for (int row=0; row < trades.size(); row++){
            try{
            	dataset.incrementValue(1, trades.get(row).getTradingBroker().getName(),trades.get(row).getStrategy().getName());
            	
            } catch (Exception ex){
                dataset.addValue(1, trades.get(row).getTradingBroker().getName(),trades.get(row).getStrategy().getName());
            }
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Strategy");
        plot.setDomainAxis(domainAxis);
        LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
        rangeAxis.setRange(new Range(0.01, 20.0));
        plot.setRangeAxis(rangeAxis);

        JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
                true);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);
        MainUI.getInstance().updateStats(chartPanel);
    }
}
