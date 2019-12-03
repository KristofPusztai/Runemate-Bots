package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CoolDispenser extends LeafTask {

    private Varbit dispensercool = Varbits.load(936);

    @Override
    public void execute(){
        SpriteItem selected = Inventory.getSelectedItem();

        if (selected == null){
            SpriteItem bucketofwater = Inventory.newQuery().names("Bucket of water").results().first();
            if (bucketofwater != null && bucketofwater.click()){
                return;
            }
        }
        GameObject bardispenser = GameObjects.newQuery().names("Bar dispenser").results().nearest();
        if (bardispenser != null && selected != null && bardispenser.interact("Use", "Bucket of water -> Bar dispenser")){
            Execution.delayUntil(() -> dispensercool.getValue() == 3, 3000,4000);
            Execution.delay(100,1200);
        }
    }
}
