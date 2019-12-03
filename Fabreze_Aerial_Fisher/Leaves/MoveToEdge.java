package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.MolchIslandBorderCoords;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.navigation.basic.CoordinatePath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToEdge extends LeafTask {

    private Player player = Players.getLocal();

    @Override
    public void execute(){
        CoordinatePath toedge = RegionPath.buildTo(MolchIslandBorderCoords.islandbordercoords.get(Random.nextInt(0, 29)));
        if (toedge != null){
            toedge.step();
            Execution.delayUntil(() -> !player.isMoving());
        }
    }
}
