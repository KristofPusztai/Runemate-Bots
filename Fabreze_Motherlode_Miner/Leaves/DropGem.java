package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.player_sense.PlayerSense;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DropGem extends LeafTask {

    private boolean hotkey = PlayerSense.getAsBoolean(PlayerSense.Key.USE_MISC_HOTKEYS);

    @Override
    public void execute(){
        SpriteItem selected = Inventory.getSelectedItem();

        SpriteItem gem = Inventory.newQuery().names("Uncut ruby","Uncut emerald", "Uncut sapphire","Uncut diamond").results().first();

        if (selected == null){
            if (gem!= null && hotkey){
                if (!Keyboard.isPressed(16)){
                    if (Keyboard.pressKey(16)){
                        Execution.delayUntil(() -> Keyboard.isPressed(16), 1000);
                    }
                }
                else if (gem.interact("Drop")){
                    Execution.delayUntil(() -> !gem.isValid(), 2000);
                    Keyboard.releaseKey(16);
                }
            }
            else if (gem != null && gem.interact("Drop")){
                Execution.delayUntil(() -> !gem.isValid(), 2000);
            }
        }
        else {
            selected.click();
            Execution.delayUntil(() -> !Inventory.isItemSelected(), 2000);
        }
    }
}
