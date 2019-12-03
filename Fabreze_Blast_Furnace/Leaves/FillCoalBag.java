package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class FillCoalBag extends LeafTask {

    private GrabCoal grabCoal = new GrabCoal();

    @Override
    public void execute(){

        SpriteItem coalbag = Inventory.newQuery().names("Coal bag").results().first();

        int coalamt = Inventory.getQuantity("Coal");

        if (!Inventory.contains("Coal")){
            grabCoal.execute();
        }
        else if (Bank.isOpen()){
            Bank.close();
        }
        else{
            if (coalbag != null){
                coalbag.click();
                Execution.delayUntil(() -> !Inventory.contains("Coal"), 2000);
                if (!Inventory.contains("Coal")){
                    ((BlastFurnaceMain) Environment.getBot()).coalbagamt += coalamt;
                }
            }
        }
    }
}
