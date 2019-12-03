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

public class InteractGap extends LeafTask {

    private Area roof7 = new Area.Rectangular(new Coordinate(3096, 3256, 3), new Coordinate(3101, 3261, 3));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").results().nearest();
        if (gap != null && gap.isVisible() && Players.getLocal() != null){
            if (gap.interact("Jump")){
                Execution.delayUntil(() -> roof7.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(gap, Random.nextDouble(0.0345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05,0.05);
            }
        }
    }

}
