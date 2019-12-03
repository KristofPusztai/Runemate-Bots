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

public class InteractEdge extends LeafTask {

    private Area end = new Area.Absolute(new Coordinate(3236,3417,0));
    @Override
    public void execute(){
        GameObject edge = GameObjects.newQuery().names("Edge").actions("Jump-off").results().nearest();
        if (edge != null && edge.isVisible() && Players.getLocal() != null){
            if (edge.interact("Jump-off")){
                Execution.delayUntil(() -> end.contains(Players.getLocal()), () ->Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(edge, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
