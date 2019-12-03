package Fabreze.bots.Fabreze_Motherlode_Miner.GUI;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

public class Location {

    private String name;
    private Area mininglocation;
    private Coordinate coordinate;

    public Location(Coordinate coordinate, Area area, String name){
        this.name = name;
        this.mininglocation = area;
        this.coordinate = coordinate;
    }
    String getName() {
        return name;
    }

    Coordinate getCoordinate(){
        return coordinate;
    }

    Area getCoords(){return mininglocation;}

}
