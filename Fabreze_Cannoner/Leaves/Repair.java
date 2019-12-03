package Fabreze.bots.Fabreze_Cannoner.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Repair extends LeafTask {
    @Override
    public void execute(){

        Magic spell = Magic.getSelected();
        if ( spell != null){//Magic selection handler
            spell.deactivate();
            Execution.delayUntil(() -> !spell.isSelected(), 2000);
            return;
        }
        SpriteItem selected = Inventory.getSelectedItem();
        if (Inventory.isItemSelected() && selected != null){//Item selection handler
            if (selected.click()){
                Execution.delayUntil(() -> !Inventory.isItemSelected(), 1500, 3000);
            }
            return;
        }

        GameObject cannon = GameObjects.newQuery().names("Broken multicannon").results().first();
        if (cannon != null && cannon.isValid()){
            if (cannon.interact("Repair")){
                Execution.delayUntil(() -> !cannon.isValid(), 3000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(cannon);
        }
    }

}
