package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import Fabreze.bots.Fabreze_Agility.Fabreze_Agility_Main;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



public class IsHealthLow extends BranchTask {

    BranchTask nextclass;

    public IsHealthLow(BranchTask nextclass){
        this.nextclass = nextclass;
    }

    private EatFood eatFood = new EatFood();
    private FoodValidator foodValidator = new FoodValidator();

    @Override
    public boolean validate(){

        return  foodValidator.checkforfood() && Health.getCurrentPercent()<30 && ((Fabreze_Agility_Main) Environment.getBot()).eatfood;
    }
    @Override
    public TreeTask successTask() { return eatFood; }

    @Override
    public TreeTask failureTask() { return new IsRunEnabled(nextclass); }
}
