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

public class InteractBeams extends LeafTask {
    private Area roof6 = new Area.Rectangular(new Coordinate(3317, 3180, 3), new Coordinate(3312, 3186, 3));


    @Override
    public void execute(){
        GameObject beams = GameObjects.newQuery().names("Roof top beams").actions("Climb").results().nearest();
        if (beams != null && beams.isVisible() && Players.getLocal() != null){
            beams.setForcedModel(new int[] {-48, -24, -63}, new int[] {-53, 1, 59});
            if (beams.interact("Climb")){
                Execution.delayUntil(() -> roof6.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(beams, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }

    }

}
