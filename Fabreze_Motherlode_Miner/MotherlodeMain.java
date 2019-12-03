package Fabreze.bots.Fabreze_Motherlode_Miner;

import Fabreze.bots.Fabreze_Motherlode_Miner.GUI.MotherlodeMinerController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.*;

public class MotherlodeMain extends TreeBot implements EmbeddableUI, SkillListener {

    public Area finalsetlocation;
    public boolean depositstatus;
    public boolean allmode;
    public boolean smithexptrig;
    public int sacksize;
    public Coordinate coordinate;
    public boolean dropgem;

    public int sack = 0;

    private void botstopper(){
        MotherlodeMinerController controller = loader.getController(); //gets controller instance for modification
        if (controller!=null){
            if (controller.stopatvalue.equals(controller.clock.getText())){
                this.stop("bot stopped");
            }
        }
    }
    @Override
    public void onStart(String... args){
        setEmbeddableUI(this);
        getEventDispatcher().addListener(this);
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        new LoopingThread(this::breakstarthandler, 1000).start();
        finalsetlocation = null; //Setting up start values for UI variables and triggers:
        depositstatus = false;
        allmode = false;
        sacksize = 0;
        smithexptrig = false;
        dropgem = false;
        new LoopingThread(this::botstopper, 1000).start();
    }

    private final FXMLLoader loader = new FXMLLoader();

    private ObjectProperty<? extends Node> botInterfaceProperty;

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                loader.setController(new MotherlodeMinerController(this));
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Motherlode_Miner/GUI/MotherlodeMinerGUI.fxml");
                final Parent document = loader.load(input);

                botInterfaceProperty = new SimpleObjectProperty<>(document);
            } catch (final IOException e) {
                Environment.getLogger().debug("Failed to load ");
                ClientUI.showAlert("Failed to load a resource ");
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }
    //Sets GUI

    public void onExperienceGained(SkillEvent event){
        MotherlodeMinerController controller = loader.getController(); //gets controller instance for modification
        controller.xp = Skill.MINING.getExperienceAsPercent(); //Reloads xp
        controller.xpgain = controller.xpgain + event.getChange(); //gets total xp gained and modifies in controller

        Platform.runLater(() -> controller.experiencepercent.setText(controller.xp + "%")); //Modifies proressbar label
        Platform.runLater(() -> controller.experiencebar.setProgress(controller.xp/100.0)); //Modifies Progressbar progress
        if (event.getSkill() == Skill.SMITHING){
            smithexptrig = true; //For fixwheel executiondelay trigger
        }
    }
    //Updates progressbar and XP Tracker upon gaining XP

    public void onLevelUp(SkillEvent event) {
        MotherlodeMinerController controller = loader.getController(); //gets controller instance for modification
        int playerlevel = Skill.MINING.getCurrentLevel();
        Platform.runLater(() -> controller.level.setText(playerlevel + "")); //modifies level text
    }
    //Updates level label

    @Override
    public TreeTask createRootTask() {
        Execution.delayUntil(() -> finalsetlocation != null);
        return new OnBreak(); }

    boolean onbreak = false;
    private int breaknumber;

    private void breakstarthandler(){
        MotherlodeMinerController controller = loader.getController();
        if (controller != null && !controller.breaktimes.isEmpty()){
            for (int i = 0 ; i < controller.breaktimes.size() ; i++){
                if (controller.breaktimes.get(i).getStartsat().equals(controller.clock.getText())){
                    onbreak = true;
                    breaknumber = i;
                    new LoopingThread(this::breakendhandler, 1000).start();
                }
            }
        }
    }
    private void breakendhandler(){
        MotherlodeMinerController controller = loader.getController();
        if (controller.breaktimes.get(breaknumber).getEndsat().equals(controller.clock.getText())){
            onbreak = false;
            GameEvents.Universal.LOGIN_HANDLER.enable();
        }
    }
}
