package Fabreze.bots.Fabreze_Minnow_Fisher;

import Fabreze.bots.Fabreze_Minnow_Fisher.GUI.MinnowGUIController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.ChatboxListener;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.MessageEvent;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;

public class MinnowFisherMain extends TreeBot implements EmbeddableUI, SkillListener, ChatboxListener {

    public boolean chattrig = false;
    private final FXMLLoader loader = new FXMLLoader();
    private ObjectProperty<? extends Node> botInterfaceProperty;


    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        System.out.println("botInterfaceProperty");
        if (botInterfaceProperty == null) {
            try {
                loader.setController(new MinnowGUIController(this));
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Minnow_Fisher/GUI/MinnowGUI.fxml");
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

    @Override
    public void onStart(String... args) {
        getEventDispatcher().addListener(this); //sets event listener
        setEmbeddableUI(this); //declares embeddable ui
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        new LoopingThread(this::botstopper, 1000).start();
        new LoopingThread(this::breakstarthandler, 1000).start();
    }

    private void botstopper(){
        MinnowGUIController controller = loader.getController(); //gets controller instance for modification
        if (controller!=null){
            if (controller.stopatvalue.equals(controller.clock.getText())){
                this.stop("bot stopped");
            }
        }
    }

    boolean onbreak = false;
    private int breaknumber;

    private void breakstarthandler(){
        MinnowGUIController controller = loader.getController();
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
        MinnowGUIController controller = loader.getController();
        if (controller.breaktimes.get(breaknumber).getEndsat().equals(controller.clock.getText())){
            onbreak = false;
            GameEvents.Universal.LOGIN_HANDLER.enable();
            }
        }

    //Adds listener to update UI
    public void onExperienceGained(SkillEvent event) {
        MinnowGUIController controller = loader.getController(); //gets controller instance for modification
        int xp = Skill.FISHING.getExperienceAsPercent(); //Reloads xp
        controller.xpgain = controller.xpgain + event.getChange(); //gets total xp gained and modifies in controller

        Platform.runLater(() -> controller.experiencepercent.setText(xp + "%")); //Modifies proressbar label
        Platform.runLater(() -> controller.experiencebar.setProgress(xp / 100.0)); //Modifies Progressbar progress
    }
    //Updates progressbar and XP Tracker upon gaining XP

    public void onLevelUp(SkillEvent event) {
        MinnowGUIController controller = loader.getController(); //gets controller instance for modification
        int playerlevel = Skill.FISHING.getCurrentLevel();
        Platform.runLater(() -> controller.level.setText(playerlevel + "")); //modifies level text
    }

    public void onMessageReceived(MessageEvent messageEvent){
        if (messageEvent.getMessage().equals("A flying fish jumps up and eats some of your minnows!")){
            chattrig = true;
        }
    }

    @Override
    public TreeTask createRootTask() {
        return new OnBreak();
    }
}
