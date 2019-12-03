package com.darksage.bots.pyramidPlunder.Data;

import com.runemate.game.api.hybrid.local.Skill;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Team Regal
 */
public enum RoomLevels {

    ROOM_21(21, Arrays.asList(21, 22, 23, 24, 25, 26, 27, 28, 29, 30)),
    ROOM_31(31, Arrays.asList(31, 32, 33, 34, 35, 36, 37, 38, 39, 40)),
    ROOM_41(41, Arrays.asList(41, 42, 43, 44, 45, 46, 47, 48, 49, 50)),
    ROOM_51(51, Arrays.asList(51, 52, 53, 54, 55, 56, 57, 58, 59, 60)),
    ROOM_61(61, Arrays.asList(61, 62, 63, 64, 65, 66, 67, 68, 69, 70)),
    ROOM_71(71, Arrays.asList(71, 72, 73, 74, 75, 76, 77, 78, 79, 80)),
    ROOM_81(81, Arrays.asList(81, 82, 83, 84, 85, 86, 87, 88, 89, 90)),
    ROOM_91(91, Arrays.asList(91, 92, 93, 94, 95, 96, 97, 98, 99));

    private int room;
    private List roomLevels;

    RoomLevels(int room, List roomLevels) {
        this.room = room;
        this.roomLevels = roomLevels;
    }

    public int getRoom() {
        return room;
    }

    public List getRoomLevels() {
        return roomLevels;
    }

    public static int getRoomLevel() {
        int level = Skill.THIEVING.getBaseLevel();
        RoomLevels[] all = RoomLevels.values();
        for (int i = 0; i < all.length; i++) {
            List levels = all[i].roomLevels;
            for (int x = 0; x < levels.size(); x++) {
                if (levels.get(x).equals(level)) {
                    return all[i].getRoom();
                }
            }
        }
        return 0;
    }
}
