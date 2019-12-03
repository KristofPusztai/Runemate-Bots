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

public class InteractLedge extends LeafTask {
    private Area roof8 = new Area.Rectangular(new Coordinate(3240, 3410, 3), new Coordinate(3236, 3415, 3));
    @Override
    public void execute(){
        GameObject ledge = GameObjects.newQuery().names("Ledge").actions("Hurdle").results().nearest();
        if (ledge != null && ledge.isVisible() && Players.getLocal() != null){
            if (ledge.interact("Hurdle")){
                Execution.delayUntil(() ->roof8.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
                Execution.delay(900);
            }
        }
        else {
            Camera.concurrentlyTurnTo(ledge, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
