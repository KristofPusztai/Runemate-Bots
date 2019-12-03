package Fabreze.bots.Fabreze_Motherlode_Miner.GUI;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

class CoordList {

    //Mining Areas
    static Area north = new Area.Rectangular(new Coordinate(3729, 5686, 0), new Coordinate(3745, 5692,0));
    static Area south = new Area.Rectangular(new Coordinate(3754, 5643, 0), new Coordinate(3729, 5635,0));
    static Area uppernorth = new Area.Polygonal(new Coordinate(3758, 5677,0), new Coordinate(3757, 5678, 0), new Coordinate(3755, 5681, 0), new Coordinate(3755, 5686, 0), new Coordinate(3761, 5686, 0), new Coordinate(3761, 5685, 0), new Coordinate(3764, 5685, 0), new Coordinate(3765, 5682, 0), new Coordinate(3765, 5677, 0), new Coordinate(3763, 5674, 0), new Coordinate(3760, 5675, 0), new Coordinate(3758, 5677, 0));
    static Area uppersouth = new Area.Polygonal(new Coordinate(3754, 5676, 0), new Coordinate(3759, 5676, 0), new Coordinate(3760, 5675, 0), new Coordinate(3763, 5675, 0), new Coordinate(3763, 5667, 0), new Coordinate(3761, 5667, 0), new Coordinate(3760, 5671, 0), new Coordinate(3758, 5672, 0), new Coordinate(3754, 5676, 0));
}