package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.Data.PyramidDoors;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class EnterPyramid extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject door = GameObjects.newQuery().names(NamesActions.DOORS.getName()).results().nearest();
        PyramidDoors doors = PyramidDoors.getDoor();
        if (doors != null) {
            if (door != null && doors.equals(PyramidDoors.NORTH_DOOR)) {
                getLogger().info("Entering North Pyramid door");
                if (door.interact("Search")) {
                    Delays.delayUntil(() -> Varbits.load(4605).getValue() == 1, () -> bot.isAnimating(), 500, 1000, 1500);
                    Execution.delay(150, 300);
                    if (Varbits.load(4605).getValue() == 1) {
                        if (!Npcs.newQuery().names(Variables.MUMMY).results().isEmpty()) {
                            getLogger().debug("North door is mummy room");
                            bot.vars.setNorthDoor(false);
                        } else {
                            bot.vars.setNorthDoor(true);
                        }
                    }
                }
            } else if (door != null && doors.equals(PyramidDoors.EAST_DOOR)) {
                getLogger().info("Entering East Pyramid door");
                if (door.interact("Search")) {
                    Delays.delayUntil(() -> Varbits.load(4605).getValue() == 1, () -> bot.isAnimating(), 500, 1000, 1500);
                    Execution.delay(150, 300);
                    if (Varbits.load(4605).getValue() == 1) {
                        if (!Npcs.newQuery().names(Variables.MUMMY).results().isEmpty()) {
                            getLogger().debug("East door is mummy room");
                            bot.vars.setEastDoor(false);
                        } else {
                            bot.vars.setEastDoor(true);
                        }
                    }
                }
            } else if (door != null && doors.equals(PyramidDoors.SOUTH_DOOR)) {
                getLogger().info("Entering South Pyramid door");
                if (door.interact("Search")) {
                    Delays.delayUntil(() -> Varbits.load(4605).getValue() == 1, () -> bot.isAnimating(), 500, 1000, 1500);
                    Execution.delay(150, 300);
                    if (Varbits.load(4605).getValue() == 1) {
                        if (!Npcs.newQuery().names(Variables.MUMMY).results().isEmpty()) {
                            getLogger().debug("South door is mummy room");
                            bot.vars.setSouthDoor(false);
                        } else {
                            bot.vars.setSouthDoor(true);
                        }
                    }
                }
            } else if (door != null && doors.equals(PyramidDoors.WEST_DOOR)) {
                getLogger().info("Entering West Pyramid door");
                if (door.interact("Search")) {
                    Delays.delayUntil(() -> Varbits.load(4605).getValue() == 1, () -> bot.isAnimating(), 500, 1000, 1500);
                    Execution.delay(150, 300);
                    if (Varbits.load(4605).getValue() == 1) {
                        if (!Npcs.newQuery().names(Variables.MUMMY).results().isEmpty()) {
                            getLogger().debug("West door is mummy room");
                            bot.vars.setWestDoor(false);
                        } else {
                            bot.vars.setWestDoor(true);
                        }
                    }
                }
            } else {
                getLogger().severe("Door is null");
            }
        } else {
            getLogger().severe("We are not near a door");
        }
    }
}
