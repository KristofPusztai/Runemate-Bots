package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsCannonDeployed extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();


    private HasAmmo hasAmmo = new HasAmmo();
    private NeedsRepair needsRepair = new NeedsRepair();


    @Override
    public boolean validate(){

        GameObject mycannon = GameObjects.newQuery().names("Dwarf multicannon").on(bot.cannon).results().nearest();

        return !Inventory.contains("Cannon base") && mycannon != null;
    }


    @Override
    public TreeTask successTask() { return hasAmmo; }

    @Override
    public TreeTask failureTask() { return needsRepair; }

}
