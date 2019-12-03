package Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.Callable;

public class MoveToStart extends LeafTask {

    private Area start = new Area.Absolute(new Coordinate(3273, 3195, 0));
    private Area roof1 = new Area.Rectangular(new Coordinate(3272, 3192, 3), new Coordinate(3274, 3180, 3));

    @Override
    public void execute(){
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(start);
        GameObject roughwall = GameObjects.newQuery().names("Rough wall").actions("Climb").results().nearest();
        if (Players.getLocal() != null){
            final Callable<Boolean> playermovingcondition = () -> Players.getLocal().isMoving();

            if (roughwall != null && roughwall.isVisible()){
                if (roughwall.interact("Climb")){
                    Execution.delayUntil(() -> roof1.contains(Players.getLocal()), playermovingcondition,2000);
                }
            }
            else if (path != null){
                path.step();
                Execution.delayUntil(() ->!Players.getLocal().isMoving(),playermovingcondition, 2000);
            }
        }
    }
}
