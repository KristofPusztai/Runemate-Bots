package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractTreeBranch2 extends LeafTask {

    private Area atobstaclenet2 = new Area.Rectangular(new Coordinate(2488, 3420, 0), new Coordinate(2483, 3425, 0));

    @Override
    public void execute(){
        GameObject treebranch2 = GameObjects.newQuery().names("Tree branch").actions("Climb-down").results().nearest();

        if (Players.getLocal() != null && treebranch2 != null && treebranch2.interact("Climb-down")){
            Execution.delayUntil(() -> atobstaclenet2.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 5000);
        }
    }
}
