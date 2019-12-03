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

public class InteractRoughWall extends LeafTask {

    private Area roof1 = new Area.Rectangular(new Coordinate(3272, 3192, 3), new Coordinate(3274, 3180, 3));

    @Override
    public void execute(){
        GameObject roughwall = GameObjects.newQuery().names("Rough wall").actions("Climb").results().nearest();
        if (roughwall != null && roughwall.isVisible() && Players.getLocal() != null){
            if (roughwall.interact("Climb")){
                Execution.delayUntil(() -> roof1.contains(Players.getLocal()), () -> Players.getLocal().isMoving(),2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(roughwall, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
