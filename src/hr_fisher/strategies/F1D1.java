package hr_fisher.strategies;/*
    Name:
    Version:
    Author(s):
    
    Description:
    
*/

import hr_fisher.user.Variables;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;
import hr_fisher.user.Variables;

public class F1D1 extends Strategy implements Runnable {

    @Override
    public void run() {
        Variables.isDropping = true;
        for(Item i : Inventory.getItems()) {
            for(int fish : Variables.chosenFishingType.getPossibleFish()) {
                if(i.getId() == fish) {
                    i.getWidgetChild().interact("Drop");
                    Time.sleep(400, 500);
                    break;
                }
            }
        }
        Variables.isDropping = false;
    }

    @Override
    public boolean validate() {
        return Inventory.getCount(Variables.chosenFishingType.getPossibleFish()) > 0;
    }
}