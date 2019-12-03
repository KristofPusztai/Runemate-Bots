package Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves;

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

    private Area end = new Area.Rectangular(new Coordinate(3299, 3194, 0));
    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").results().nearest();
        if (gap != null && gap.isVisible() && Players.getLocal() != null){
            if (gap.interact("Jump")){
                Execution.delayUntil(() -> end.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
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
