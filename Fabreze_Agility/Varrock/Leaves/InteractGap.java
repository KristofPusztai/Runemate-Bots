package Fabreze.bots.Fabreze_Agility.Varrock.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractGap extends LeafTask {
    private Area roof3 = new Area.Rectangular(new Coordinate(3197, 3416, 1), new Coordinate(3194, 3416, 1));
    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Leap").results().nearest();
        if (gap != null && gap.isVisible() && Players.getLocal() != null){
            if (gap.interact("Leap")){
                Execution.delayUntil(() -> roof3.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
                Execution.delay(900);
            }
        }
        else {
            Camera.concurrentlyTurnTo(gap, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
