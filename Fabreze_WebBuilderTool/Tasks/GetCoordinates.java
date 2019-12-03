package Fabreze.bots.Fabreze_WebBuilderTool.Tasks;

import Fabreze.bots.Fabreze_WebBuilderTool.WebToolMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.web.WebVertex;
import com.runemate.game.api.hybrid.location.navigation.web.vertex_types.CoordinateVertex;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.task.Task;

import java.util.Collections;
import java.util.Set;

public class GetCoordinates extends Task {

    private WebToolMain bot = (WebToolMain)Environment.getBot();

    private Player player = Players.getLocal();
    private CoordinateVertex coordinateVertex;

    @Override
    public boolean validate() {

        boolean bool = false;

        Set <Coordinate> coordinates = player.getPosition().getReachableCoordinates();
        for (Coordinate i:coordinates){
            if (i.distanceTo(player.getPosition()) <= 0){
                coordinateVertex = new CoordinateVertex(i, Collections.emptyList());
                if (!bot.webVertexCollection.contains(coordinateVertex)){
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }

    @Override
    public void execute() {

        for (WebVertex i: bot.webVertexCollection){
            if (i.distanceTo(coordinateVertex, Distance.Algorithm.CHEBYSHEV) <= 1){
                coordinateVertex.addBidirectionalEdge(i);
            }
        }
        bot.webVertexCollection.add(coordinateVertex);
    }


}