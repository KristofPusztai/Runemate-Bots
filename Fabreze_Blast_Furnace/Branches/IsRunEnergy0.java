package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DrinkStaminaPot;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsRunEnergy0 extends BranchTask {

    private IsBucketOfWaterSelected isBucketOfWaterSelected = new IsBucketOfWaterSelected();
    private DrinkStaminaPot drinkStaminaPot = new DrinkStaminaPot();

    @Override
    public boolean validate(){
        return Traversal.getRunEnergy() <=5 ;
    }
    @Override
    public TreeTask successTask() { return drinkStaminaPot; }

    @Override
    public TreeTask failureTask() { return isBucketOfWaterSelected; }
}
