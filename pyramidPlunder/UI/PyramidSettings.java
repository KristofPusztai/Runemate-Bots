package Fabreze.bots.pyramidPlunder.UI;

import com.darksage.bots.pyramidPlunder.Data.Banking;
import com.darksage.bots.pyramidPlunder.Data.Food;
import com.darksage.bots.pyramidPlunder.Data.Potions;
import com.darksage.bots.pyramidPlunder.Data.PyramidReturn;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * Created by Team Regal
 */
public class PyramidSettings extends VBox implements Initializable {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @FXML
    private ComboBox<String> ComboFood, ComboSceptre, ComboBank, ComboPotion;
    @FXML
    private Button ButtonStart;
    @FXML
    private VBox VBoxSettings;

    private String food;

    public PyramidSettings(PyramidPlunder bot) {
        this.bot = bot;

        FXMLLoader loader = new FXMLLoader();

        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/darksage/bots/pyramidPlunder/UI/PyramidPlunder.fxml"));

        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboFood.getItems().addAll(FXCollections.observableArrayList(Food.getNames()));
        ComboFood.setPromptText("Food");
        ComboFood.setOnAction(onFoodChosen());
        ComboSceptre.getItems().addAll(FXCollections.observableArrayList(PyramidReturn.getChoices()));
        ComboSceptre.setPromptText("Sceptre used?");
        ComboSceptre.setOnAction(onSceptreChosen());
        ComboBank.getItems().addAll(FXCollections.observableArrayList(Banking.getChoices()));
        ComboBank.setVisible(false);
        ComboBank.setPromptText("Bank location");
        ComboBank.setOnAction(onBankChosen());
        ComboPotion.getItems().addAll(FXCollections.observableArrayList(Potions.getChoices()));
        ComboPotion.setPromptText("Potions");
        ComboPotion.setOnAction(onPotionChosen());
        ButtonStart.setOnAction(onStartPressed());
    }

    private String potion;

    private EventHandler<ActionEvent> onPotionChosen() {
        return (ActionEvent event) -> {
            if ((potion = ComboPotion.getSelectionModel().getSelectedItem()) != null) {
                Potions choice = Potions.getValue(potion);
                if (choice != null) {
                    bot.vars.setPotion(Pattern.compile(choice.getPotionPattern()));
                }
            }
        };
    }

    private String bank;

    private EventHandler<ActionEvent> onBankChosen() {
        return (ActionEvent event) -> {
            if ((bank = ComboBank.getSelectionModel().getSelectedItem()) != null) {
                Banking choice = Banking.getData(bank);
                if (choice != null) {
                    bot.vars.setLocalBanking(choice.isLocalBanking());
                }
            }
        };
    }

    private String sceptre;

    private EventHandler<ActionEvent> onSceptreChosen() {
        return (ActionEvent event) -> {
            if ((sceptre = ComboSceptre.getSelectionModel().getSelectedItem()) != null) {
                PyramidReturn choice = PyramidReturn.getData(sceptre);
                if (choice != null) {
                    bot.vars.setSceptreUsed(choice.isSceptre());
                    ComboBank.setVisible(choice.isSceptre());
                }
            }
        };
    }

    private EventHandler<ActionEvent> onStartPressed() {
        return (ActionEvent event) -> {
            bot.vars.setUiSet(true);
        };
    }

    private EventHandler<ActionEvent> onFoodChosen() {
        return (ActionEvent event) -> {
            if ((food = ComboFood.getSelectionModel().getSelectedItem()) != null) {
                Food choice = Food.getValue(food);
                if (choice != null) {
                    bot.vars.setFood(choice.getFoodName());
                }
            }
        };
    }
}
