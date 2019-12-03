package com.darksage.bots.pyramidPlunder.Data;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;

/**
 * Created by Team Regal
 */
public enum PyramidDoors {

    NORTH_DOOR(new Area.Circular(new Coordinate(3288, 2802, 0), 2)),
    SOUTH_DOOR(new Area.Circular(new Coordinate(3289, 2788, 0), 2)),
    EAST_DOOR(new Area.Circular(new Coordinate(3296, 2795, 0), 2)),
    WEST_DOOR(new Area.Circular(new Coordinate(3281, 2794, 0), 2));

    private Area.Circular doorArea;

    PyramidDoors(Area.Circular doorArea) {
        this.doorArea = doorArea;
    }

    public Area.Circular getDoorArea() {
        return doorArea;
    }

    public static PyramidDoors getDoor() {
        Player me = Players.getLocal();
        if (PyramidDoors.NORTH_DOOR.getDoorArea().contains(me))
            return PyramidDoors.NORTH_DOOR;
        if (PyramidDoors.SOUTH_DOOR.getDoorArea().contains(me))
            return PyramidDoors.SOUTH_DOOR;
        if (PyramidDoors.EAST_DOOR.getDoorArea().contains(me))
            return PyramidDoors.EAST_DOOR;
        if (PyramidDoors.WEST_DOOR.getDoorArea().contains(me))
            return PyramidDoors.WEST_DOOR;
        return null;
    }
}
