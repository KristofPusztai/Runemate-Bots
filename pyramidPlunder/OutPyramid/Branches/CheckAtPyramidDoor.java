package com.darksage.bots.pyramidPlunder.OutPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Data.PyramidDoors;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.EnterPyramid;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.WalkToPyramidDoor;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckAtPyramidDoor extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private WalkToPyramidDoor walk = new WalkToPyramidDoor();
    private EnterPyramid enter = new EnterPyramid();

    @Override
    public TreeTask failureTask() {
        return walk;
    }

    @Override
    public TreeTask successTask() {
        return enter;
    }

    @Override
    public boolean validate() {
        PyramidDoors doors = PyramidDoors.getDoor();
        if (!bot.vars.isNorthDoor()) {
            return doors != null && doors.equals(PyramidDoors.NORTH_DOOR);
        } else if (!bot.vars.isEastDoor()) {
            return doors != null && doors.equals(PyramidDoors.EAST_DOOR);
        } else if (!bot.vars.isSouthDoor()) {
            return doors != null && doors.equals(PyramidDoors.SOUTH_DOOR);
        } else if (!bot.vars.isWestDoor()) {
            return doors != null && doors.equals(PyramidDoors.WEST_DOOR);
        }
        return false;
    }
}
