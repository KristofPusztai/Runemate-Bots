package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.darksage.bots.pyramidPlunder.Data.PyramidDoors;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class WalkToPyramidDoor extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private Path path;

    @Override
    public void execute() {
        if (bot.vars.isNorthDoor() && bot.vars.isSouthDoor() && bot.vars.isEastDoor() && bot.vars.isWestDoor()) {
            bot.vars.setNorthDoor(false);
            bot.vars.setSouthDoor(false);
            bot.vars.setWestDoor(false);
            bot.vars.setEastDoor(false);
        }
        if (!bot.vars.isNorthDoor()) {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(PyramidDoors.NORTH_DOOR.getDoorArea());
            getLogger().info("Walking to North door");
        } else if (!bot.vars.isEastDoor()) {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(PyramidDoors.EAST_DOOR.getDoorArea());
            getLogger().info("Walking to East door");
        } else if (!bot.vars.isSouthDoor()) {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(PyramidDoors.SOUTH_DOOR.getDoorArea());
            getLogger().info("Walking to South door");
        } else if (!bot.vars.isWestDoor()) {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(PyramidDoors.WEST_DOOR.getDoorArea());
            getLogger().info("Walking to West door");
        }
        if (path != null) {
            path.step(true);
        } else {
            getLogger().severe("Path is null");
        }
    }
}
