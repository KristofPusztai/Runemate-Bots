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

public class InteractNarroWall extends LeafTask {

    private Area roof5 = new Area.Rectangular(new Coordinate(3088, 3261, 3), new Coordinate(3088, 3257, 3));

    @Override
    public void execute(){
        GameObject narrowall = GameObjects.newQuery().names("Narrow wall").actions("Balance").results().nearest();
        if (narrowall != null && narrowall.isVisible() && Players.getLocal() != null){
            if (narrowall.interact("Balance")){
                Execution.delayUntil(() -> roof5.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(narrowall, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }
}
