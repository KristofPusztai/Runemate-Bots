package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractCable;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtCable extends BranchTask {

    private IsAtZipline isAtZipline = new IsAtZipline();
    private InteractCable interactCable = new InteractCable();

    private Area roof2=  new Area.Rectangular(new Coordinate(3272,3172, 3), new Coordinate(3265, 3161, 3));
    @Override
    public boolean validate(){
        return roof2.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactCable; }

    @Override
    public TreeTask failureTask() { return isAtZipline; }
}
