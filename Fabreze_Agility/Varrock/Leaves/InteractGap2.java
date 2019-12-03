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

public class InteractGap2 extends LeafTask {
    private Area roof5 = new Area.Rectangular(new Coordinate(3201,3398, 3), new Coordinate(3185, 3398, 3));

    @Override
    public void execute(){
        GameObject gap2 = GameObjects.newQuery().names("Gap").actions("Leap").results().nearest();
        if (gap2 != null && gap2.isVisible() && Players.getLocal() != null){
            if (gap2.interact("Leap")){
                Execution.delayUntil(() -> roof5.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(gap2, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
