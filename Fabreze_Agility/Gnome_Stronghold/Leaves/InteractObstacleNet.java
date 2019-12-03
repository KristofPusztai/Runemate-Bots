package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractObstacleNet extends LeafTask {

    private Area attreebranch = new Area.Rectangular(new Coordinate(2471, 3424, 1), new Coordinate(2476, 3422, 1));

    @Override
    public void execute(){
        GameObject obstaclenet = GameObjects.newQuery().names("Obstacle net").actions("Climb-over").results().nearest();

        if (obstaclenet != null && obstaclenet.isVisible()){
            if (obstaclenet.interact("Climb-over")){
                Execution.delayUntil(() -> attreebranch.contains(Players.getLocal()), 6000, 7000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(obstaclenet, Random.nextDouble(0.3, 0.845));
            Camera.setZoom(0.05, 0.1);
        }
    }
}
