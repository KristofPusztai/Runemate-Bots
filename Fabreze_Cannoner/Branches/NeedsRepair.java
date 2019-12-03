package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.Leaves.Deploy;
import Fabreze.bots.Fabreze_Cannoner.Leaves.Repair;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NeedsRepair extends BranchTask {


    private Repair repair = new Repair();
    private Deploy deploy = new Deploy();

    @Override
    public boolean validate(){
        GameObject cannon = GameObjects.newQuery().names("Broken multicannon").results().first();

        return cannon != null;
    }


    @Override
    public TreeTask successTask() { return repair; }

    @Override
    public TreeTask failureTask() { return deploy; }

}
