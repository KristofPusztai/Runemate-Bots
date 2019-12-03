package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractTreeBranch extends LeafTask {

    private Area atrope = new Area.Rectangular(new Coordinate(2472, 3420, 2), new Coordinate(2477, 3419, 2));

    @Override
    public void execute(){
        GameObject treebranch = GameObjects.newQuery().names("Tree branch").actions("Climb").results().nearest();

        if (treebranch != null && treebranch.interact("Climb")){
            Execution.delayUntil(()-> atrope.contains(Players.getLocal()), 5000);
        }
    }
}
