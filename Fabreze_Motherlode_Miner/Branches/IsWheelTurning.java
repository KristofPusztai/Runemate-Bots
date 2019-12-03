package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.FixWheels;
import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.MoveToSack;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsWheelTurning extends BranchTask {

    private MoveToSack moveToSack = new MoveToSack();
    private FixWheels fixWheels = new FixWheels();

    @Override
    public boolean validate(){
        if( ChatDialog.getContinue() != null || GameObjects.newQuery().names("Broken strut").actions("Hammer").results().size() == 2){
            return true;
        }
        else{
            SpriteItem hammer = Inventory.newQuery().names("Hammer").actions("Drop").results().first();
            if (Inventory.contains("Hammer") && hammer != null && hammer.interact("Drop")){
                return false;
            }
            else {return false;}
        }
    }

    @Override
    public TreeTask successTask(){return fixWheels;}

    @Override
    public TreeTask failureTask(){return moveToSack;}
}
