package carte.game.khess.controllers

import carte.toolfx.core.Controller
import carte.toolfx.core.Element
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import java.net.URL
import java.util.*


@Element("/fxml/nodes/board.fxml")
class BoardController : Controller() {

    @FXML
    lateinit var boardPane: GridPane

    override fun onCreate() {


    }
}