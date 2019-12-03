package Fabreze.bots.Fabreze_Fighter.Leaves;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GrabLoot extends LeafTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    private GroundItem object;

    public GrabLoot(GroundItem loot){
        object = loot;
    }

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

        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();

        if (Inventory.isFull() && bot.eatfoodtoloot && food != null){
            if (food.interact("Eat")){
                Execution.delayUntil(() -> !Inventory.isFull(), 2000);
            }
            return;
        }

        Player player = Players.getLocal();

        if (player != null && object != null){

            ItemDefinition definition = object.getDefinition();

           if (object.isVisible() && definition != null){
               if (object.interact("Take", definition.getName())){
                   Execution.delayUntil(() -> bot.lootadded, player::isMoving,2000);
                   bot.lootadded = false;
                   }
               }
        else {
            Camera.concurrentlyTurnTo(object);
            }
        }
    }

}
