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

public class InteractObstacleNet2 extends LeafTask {

    private Area pipe = new Area.Rectangular(new Coordinate(2488, 3427, 0), new Coordinate(2482, 3431, 0));

    @Override
    public void execute(){

        GameObject obstaaclenet2 = GameObjects.newQuery().names("Obstacle net").actions("Climb-over").on(new Coordinate(2483, 3426, 0)).results().first();

        if (Players.getLocal() != null && obstaaclenet2 != null && obstaaclenet2.isVisible()){
            if (obstaaclenet2.interact("Climb-over")){
                Execution.delayUntil(() -> pipe.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 5000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(obstaaclenet2, Random.nextDouble(0.3, 0.845));
        }
    }
}
