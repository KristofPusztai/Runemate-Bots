package Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractCrate extends LeafTask {

    private Area end = new Area.Rectangular(new Coordinate(3103, 3261, 0));

    @Override
    public void execute(){
        GameObject crate = GameObjects.newQuery().names("Crate").actions("Climb-down").results().nearest();
        if (crate != null && crate.isVisible() && Players.getLocal() != null){
            if (crate.interact("Climb-down")){
                Execution.delayUntil(() -> end.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(crate, Random.nextDouble(0.345, 0.849));
        }
    }

}
