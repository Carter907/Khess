package carte.game.khess.controllers

import carte.game.khess.Board
import carte.game.khess.BoardLinker
import carte.toolfx.core.Controller
import carte.toolfx.core.Screen
import carte.toolfx.core.runFxmlElement
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import java.net.URL
import java.util.*


@Screen("/fxml/screens/home.fxml")
class HomeController : Controller() {

    @FXML
    lateinit var backgroundPane: StackPane;
    override fun onCreate() {
        val boardPaneController = runFxmlElement<BoardController>(this);

        backgroundPane.prefWidthProperty().bind(scene.widthProperty())
        scene.widthProperty().addListener { obs ->
            println(backgroundPane.width)
        };

        val board = Board();
        val boardLinker = BoardLinker(boardPaneController, board, boardPaneController.boardPane);
        boardLinker.link()
        backgroundPane.children.add(boardPaneController.boardPane);


    }


}