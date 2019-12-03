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

public class InteractWall extends LeafTask {
    private Area roof4 = new Area.Rectangular(new Coordinate(3192, 3406, 3), new Coordinate(3198, 3402, 3));
    @Override
    public void execute(){
        GameObject wall = GameObjects.newQuery().names("Wall").actions("Balance").results().nearest();
        if (wall != null && wall.isVisible() && Players.getLocal() != null){
            if (wall.interact("Balance")){
                Execution.delayUntil(() -> roof4.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);

            }
        }
        else {
            Camera.concurrentlyTurnTo(wall, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
