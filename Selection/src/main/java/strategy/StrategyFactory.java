package strategy;

/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class StrategyFactory {
    public StrategyInterface create(String strategy){
        switch (strategy) {
            case "Swing":
                return new StrategySwing();
            case "PositionInvesting":
                return new StrategyPositionInvesting();
            case "ScalpingInvesting":
                return new StrategyScalping();
            case "DayTending":
                return new StrategyDayTrending();
        }
		return null;
    }
}
