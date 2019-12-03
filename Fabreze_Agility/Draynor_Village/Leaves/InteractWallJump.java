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

public class InteractWallJump extends LeafTask {

    private Area roof6 = new Area.Rectangular(new Coordinate(3088, 3255, 3), new Coordinate(3094, 3255, 3));

    @Override
    public void execute(){
        GameObject wall = GameObjects.newQuery().names("Wall").actions("Jump-up").results().nearest();
        if (wall != null && wall.isVisible() && Players.getLocal() != null){
            if (wall.interact("Jump-up")){
                Execution.delayUntil(() -> roof6.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 3000);
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
