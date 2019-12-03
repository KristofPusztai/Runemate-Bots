package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import Fabreze.bots.Fabreze_Agility.Fabreze_Agility_Main;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsMarksOfGrace extends BranchTask {

    BranchTask nextclass;
    private GetMarksOfGrace getMarksOfGrace = new GetMarksOfGrace();

    public IsMarksOfGrace(BranchTask nextclass){
        this.nextclass = nextclass;
    }
    @Override
    public boolean validate() {
        GroundItem markofgrace = GroundItems.newQuery().names("Mark of grace").reachable().results().nearest();
        return markofgrace != null && ((Fabreze_Agility_Main) Environment.getBot()).markofgrace;
    }

    @Override
    public TreeTask failureTask() { return nextclass; }

    @Override
    public TreeTask successTask() {
        return getMarksOfGrace;
    }
}
