package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import Fabreze.bots.Fabreze_Aerial_Fisher.Branches.IsInvFull;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class DropFish extends LeafTask {

    IsInvFull isInvFull;
    public DropFish(IsInvFull isinvfullclass){
        this.isInvFull = isinvfullclass;
    }

    @Override
    public void execute(){
        SpriteItem selected = Inventory.getSelectedItem();

        if (selected == null) {
            if (Inventory.containsAnyOf("Bluegill","Common tench","Mottled eel","Greater siren")){
                SpriteItem fish = Inventory.newQuery().names("Bluegill","Common tench","Mottled eel","Greater siren").actions("Drop").results().sortByIndex().first();
                if (!Keyboard.isPressed(16) && Keyboard.pressKey(16)){
                    Execution.delay(400);
                }
                else if (fish != null && fish.interact("Drop")){
                    Execution.delayWhile(fish::isValid, 2000);
                }
            }
            else if (!Inventory.containsAnyOf("Bluegill","Common tench","Mottled eel","Greater siren")){
                if (Keyboard.isPressed(16)){
                    Keyboard.releaseKey(16);
                }
                isInvFull.inventorytrig = false;
            }
        }
        else {
            selected.click();
            Execution.delayUntil(() -> !Inventory.isItemSelected(), 1500, 3000);
        }
    }
}
