package carte.game.khess.controllers

import carte.toolfx.core.Controller
import carte.toolfx.core.Element
import carte.toolfx.core.Screen
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import java.net.URL
import java.util.*


@Element("/fxml/nodes/square.fxml")
class SquareController : Controller() {

    @FXML
    lateinit var squareRoot: StackPane;
    @FXML
    lateinit var colorRoot: Pane;


    override fun onCreate() {



    }

}