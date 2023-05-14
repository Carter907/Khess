package carte.game.khess.controllers

import carte.toolfx.core.Controller
import carte.toolfx.core.Element
import carte.toolfx.core.Screen
import javafx.fxml.FXML
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color

@Element("/fxml/nodes/piece-pane.fxml")
class PiecePaneController : Controller() {

    @FXML
    lateinit var piecePane: Pane;

    override fun onCreate() {


    }
}