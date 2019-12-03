package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import Fabreze.bots.Fabreze_Agility.Fabreze_Agility_Main;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GetMarksOfGrace extends LeafTask {
    @Override
    public void execute(){
        GroundItem markofgrace = GroundItems.newQuery().names("Mark of grace").reachable().results().nearest();
        if (Players.getLocal() != null && markofgrace != null && markofgrace.getDefinition() != null && markofgrace.isVisible()){
            String interaction = markofgrace.getDefinition().getGroundActions().get(0);
            if (markofgrace.interact(interaction)){
                Execution.delayUntil(() -> ((Fabreze_Agility_Main) Environment.getBot()).markofgracetrig, () -> Players.getLocal().isMoving(), 2000);
                ((Fabreze_Agility_Main) Environment.getBot()).markofgracetrig = false;
            }
        }
        else {
            Camera.concurrentlyTurnTo(markofgrace, Random.nextDouble(0.3, 0.845) );
        }
    }
}
