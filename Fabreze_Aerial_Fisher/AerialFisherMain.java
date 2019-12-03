package Fabreze.bots.Fabreze_Aerial_Fisher;

import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.AerialFishingGUIController;
import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.MolchIslandBorderCoords;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
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

public class AerialFisherMain extends TreeBot implements EmbeddableUI, SkillListener, ChatboxListener {

    public boolean chattrig = false;
    public boolean fishchunks = false;

    private final FXMLLoader loader = new FXMLLoader();

    private ObjectProperty<? extends Node> botInterfaceProperty;


    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        System.out.println("botInterfaceProperty");
        if (botInterfaceProperty == null) {
            try {
                loader.setController(new AerialFishingGUIController(this));
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Aerial_Fisher/GUI/AerialFishingGUI.fxml");
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
        AerialFishingGUIController.invcheck = 0;
        getEventDispatcher().addListener(this); //sets event listener
        setEmbeddableUI(this); //declares embeddable ui
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        new LoopingThread(this::botstopper, 1000).start();
        MolchIslandBorderCoords.setIslandbordercoords(); //Sets border coordinates of Molch Island in an array list
    }

    private void botstopper(){
        AerialFishingGUIController controller = loader.getController(); //gets controller instance for modification
        if (controller!=null){
            if (controller.stopatvalue.equals(controller.clock.getText())){
                this.stop("bot stopped");
            }
        }
    }
    public boolean cutfishtrigger = false;
    //Adds listener to update UI
    public void onExperienceGained(SkillEvent event) {
        AerialFishingGUIController controller = loader.getController(); //gets controller instance for modification
        int fishingxp = Skill.FISHING.getExperienceAsPercent(); //Reloads fishingxp
        int huntterxp = Skill.HUNTER.getExperienceAsPercent();//Reloads hunterxp
        int cookingxp = Skill.COOKING.getExperienceAsPercent();

        if (event.getSkill() == Skill.COOKING){
            cutfishtrigger = true;
            controller.cookingxpgain = controller.cookingxpgain + event.getChange();
            Execution.delay(500);
            cutfishtrigger = false;
        }
        if (event.getSkill() == Skill.FISHING){
            controller.fishingxpgain = controller.fishingxpgain + event.getChange(); //gets total fishingxp gained and modifies in controller
            }
        if (event.getSkill() == Skill.HUNTER){
            controller.huntingxpgain = controller.huntingxpgain+event.getChange(); // gets total hunter xp
            }

        Platform.runLater(() -> controller.fishingexperiencepercent.setText(fishingxp + "%")); //Modifies fishing proressbar label
        Platform.runLater(() -> controller.fishingexperiencebar.setProgress(fishingxp / 100.0)); //Modifies fishing Progressbar progress
        Platform.runLater(() -> controller.hunterexperiencepercent.setText(huntterxp +"%")); //Modifies hunter progressbar label
        Platform.runLater(() -> controller.hunterexperiencebar.setProgress(huntterxp /100.0)); //Modifies hunter progressbar progress
        Platform.runLater(() -> controller.cookingexperiencebar.setProgress(cookingxp / 100.0));
        Platform.runLater(() -> controller.cookingexperiencepercent.setText(cookingxp + "%"));
    }
    //Updates progressbar and XP Tracker upon gaining XP

    public void onLevelUp(SkillEvent event) {
        AerialFishingGUIController controller = loader.getController(); //gets controller instance for modification
        int playerlevel = Skill.FISHING.getCurrentLevel();
        Platform.runLater(() -> controller.fishinglevel.setText(playerlevel + "")); //modifies level text
    }

    public void onMessageReceived(MessageEvent messageEvent){
        if (messageEvent.getMessage().equals("Your cormorant returns with its catch.")){
            chattrig = true;
        }
    }
        @Override
    public TreeTask createRootTask() {
        return new IsOnMolchIsland();
    }
}
