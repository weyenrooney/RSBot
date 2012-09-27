package hr_fisher.strategies;/*
    Name:
    Version:
    Author(s):
    
    Description:
    
*/

import hr_fisher.locations.Karamja;
import hr_fisher.user.Util;
import hr_fisher.user.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class WalkToFishingSpot extends Node {

    @Override
    public void execute() {

        Variables.chosenLocation.walkToFishingSpot();
    }

    @Override
    public boolean activate() {

        if (!Variables.hasStarted)
            return false;

        if (DepositBox.isOpen()) {
            int[] fish = Variables.chosenFishingType.getPossibleFish();
            if (DepositBox.getItemCount(fish) > 0) {
                return false;
            }

        }

        if (Bank.isOpen() && Inventory.getCount(Variables.chosenFishingType.getPossibleFish()) > 0)
            return false;

        NPC closestFishingSpot = Util.getClosestFishingSpot();

        if(closestFishingSpot != null && Calculations.distanceTo(closestFishingSpot) < 10)
        {
            return false;
        }

        return Util.hasNeededItems()
                && !Inventory.isFull()
                && Players.getLocal().getAnimation() == -1
                && (closestFishingSpot == null || !closestFishingSpot.isOnScreen());

    }
}
