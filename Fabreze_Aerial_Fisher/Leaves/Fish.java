package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import Fabreze.bots.Fabreze_Aerial_Fisher.AerialFisherMain;
import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.AerialFishingGUIController;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.List;

/**
 * NOTES:
 * Clicks on fishing spots
 */
public class Fish extends LeafTask {

    @Override
    public void execute() {

        Npc fishingspot = Npcs.newQuery().names("Fishing spot").actions("Catch").results().nearest();

        if (fishingspot != null && fishingspot.isVisible() && !Inventory.isItemSelected()){
            if (fishingspot.interact("Catch")){
                if (AerialFishingGUIController.invcheck == 1){ //Fish 1 Drop 1 Method
                    Keyboard.pressKey(16);
                    Execution.delay(900);
                    List<SpriteItem> listOfItems = Inventory.getItems().asList();
                    listOfItems.forEach(a -> {
                        ItemDefinition def = a.getDefinition();
                        if (def != null && def.getName().matches("Bluegill|Common tench|Mottled eel|Greater siren")){
                            a.interact("Drop");
                        }
                    });
                    Keyboard.releaseKey(16);
                }
                Execution.delay(900);
                Execution.delayUntil(() -> ((AerialFisherMain)Environment.getBot()).chattrig, 5000);
                ((AerialFisherMain)Environment.getBot()).chattrig = false;
            }
        }
        else{
            if (Inventory.isItemSelected() && Inventory.getSelectedItem() != null){
                Inventory.getSelectedItem().click();
                Execution.delayUntil(() -> !Inventory.isItemSelected(), 1500, 3000);
            }
            else{
                Camera.concurrentlyTurnTo(fishingspot);
            }
        }
    }
}
