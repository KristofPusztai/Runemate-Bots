package Fabreze.bots.Fabreze_Barrows;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;

public class BarrowsMain extends TreeBot implements EmbeddableUI {

    public void onStart(String... args){
        setEmbeddableUI(this);
    }

    private final FXMLLoader loader = new FXMLLoader();

    private ObjectProperty<? extends Node> botInterfaceProperty;

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                //loader.setController();
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Barrows/GUI/BarrowsUI.fxml");
                final Parent document = loader.load(input);

                botInterfaceProperty = new SimpleObjectProperty<>(document);
            } catch (final IOException e) {
                Environment.getLogger().debug("Failed to load ");
                ClientUI.showAlert("Failed to load a resource ");
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }//Sets GUI
    @Override
    public TreeTask createRootTask() {return null;}
}
