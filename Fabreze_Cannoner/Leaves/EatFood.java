package Fabreze.bots.Fabreze_Cannoner.Leaves;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {

    private boolean displayhealtherror = false;

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

        int healthcache = Health.getCurrent();
        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();

        if (food != null && food.interact("Eat")){
            Execution.delayUntil(() -> Health.getCurrent() > healthcache, 1200, 2000);
        }
        else if (food == null && !displayhealtherror){
            ClientUI.showAlert(BotLogger.Level.WARN, "Health is Below Specified Level and No Food Is Found In Inventory");
            displayhealtherror = true;
        }
    }

}
