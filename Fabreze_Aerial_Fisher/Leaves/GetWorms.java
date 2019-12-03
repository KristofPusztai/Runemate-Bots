package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.AerialFishingGUIController;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;



public class GetWorms extends LeafTask {

    private Coordinate worms = new Coordinate(1365, 3632, 0);
    final RegionPath path = RegionPath.buildTo(worms);

    @Override
    public void execute(){

        GameObject kingworm = GameObjects.newQuery().names("King worm").actions("Take").on(new Coordinate(1365, 3632, 0)).results().first();
        Player player = Players.getLocal();

        if (player != null && kingworm != null && kingworm.isVisible()){
            while (Inventory.containsAnyOf("Bluegill", "Common tench", "Mottled eel", "Greater siren")){
                Keyboard.pressKey(16);
                SpriteItem fish = Inventory.newQuery().names("Bluegill", "Common tench", "Mottled eel", "Greater siren").results().first();
                if (fish != null){
                    fish.interact("Drop");
                }
            }
            Keyboard.releaseKey(16);
            if (AerialFishingGUIController.invcheck == 1){
                int inventoryspace = Inventory.getEmptySlots()-2;

                while (Inventory.getQuantity("King worm") < inventoryspace){
                    kingworm.interact("Take");
                    Execution.delay(1500, 2000);
                }
            }
            else {
                while (Inventory.getQuantity("King worm") < Random.nextInt(8,14)){
                    kingworm.interact("Take");
                    Execution.delay(1500, 2000);
                    }
                }
            }
        else{
            if (path != null){
                path.step();
            }
            Camera.concurrentlyTurnTo(kingworm);
        }
    }
}

