package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractPipe extends LeafTask {

    private Area postpipe = new Area.Absolute(new Coordinate(2484, 3437, 0), new Coordinate(2487, 3437, 0));

    @Override
    public void execute(){
        GameObject pipe = GameObjects.newQuery().names("Obstacle pipe").actions("Squeeze-through").results().nearest();

        if (Players.getLocal() != null && pipe != null && pipe.interact("Squeeze-through")){
            Execution.delayUntil(() -> postpipe.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 6000);
        }
    }
}
