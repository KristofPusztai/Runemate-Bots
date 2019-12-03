package Fabreze.bots.Fabreze_WebBuilderTool.UI;

import Fabreze.bots.Fabreze_WebBuilderTool.WebToolMain;
import com.runemate.game.api.hybrid.location.navigation.web.WebVertex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class WebToolUIController implements Initializable {

    private WebToolMain bot;
    public WebToolUIController(WebToolMain bot){
        this.bot = bot;
    }

    @FXML
    private ListView<WebVertex> coordinateview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coordinateview.setItems(bot.webVertexCollection);

    }


}
