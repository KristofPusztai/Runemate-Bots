package Fabreze.bots.Fabreze_Aerial_Fisher.Branches;

import Fabreze.bots.Fabreze_Aerial_Fisher.AerialFisherMain;
import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.CutFish;
import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.DropFish;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCuttingFishEnabled extends BranchTask {

    private CutFish cutFish = new CutFish();

    LeafTask nextclass;
    public IsCuttingFishEnabled(LeafTask nextclassiffalse){
        this.nextclass = nextclassiffalse;
    }

    @Override
    public boolean validate(){
        return ((AerialFisherMain) Environment.getBot()).fishchunks && Inventory.contains("Knife") && Inventory.containsAnyOf("Bluegill","Common tench","Mottled eel","Greater siren");
    }
    @Override
    public TreeTask successTask() { return cutFish; }

    @Override
    public TreeTask failureTask() { return nextclass; }
}
