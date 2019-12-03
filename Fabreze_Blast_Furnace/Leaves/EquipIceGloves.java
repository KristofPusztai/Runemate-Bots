package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipIceGloves extends LeafTask {

    private GrabIceGloves grabIceGloves = new GrabIceGloves();

    @Override
    public void execute(){
        SpriteItem icegloves = Inventory.newQuery().names("Ice gloves").results().first();
        if (Bank.isOpen()){
            Bank.close();
        }
        else if (Inventory.contains("Ice gloves") && icegloves != null && icegloves.click()){
            Execution.delayUntil(() -> Equipment.contains("Ice gloves"), 2000);
        }
        else {
            grabIceGloves.execute();
        }
    }
}
