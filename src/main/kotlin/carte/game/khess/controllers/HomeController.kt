package carte.game.khess.controllers

import carte.game.khess.model.Board
import carte.game.khess.BoardLinker
import carte.game.khess.model.Team
import carte.toolfx.core.Controller
import carte.toolfx.core.Screen
import carte.toolfx.core.runFxmlElement
import javafx.fxml.FXML
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.layout.StackPane


@Screen("/fxml/screens/home.fxml")
class HomeController : Controller() {

    @FXML
    lateinit var backgroundPane: StackPane;
    override fun onCreate() {

        /*
        * creating the board pane controller to get access to the fxml nodes. these nodes make of the background
        * ui surrounding the board.
        *
        * */

        val dialog: Dialog<String> = Dialog();
        dialog.dialogPane.buttonTypes.add(ButtonType.OK)
        dialog.title = "welcome"
        dialog.dialogPane.minHeight = 250.0;
        dialog.contentText = """
            Welcome to Khess!
            
            Khess is a Chess game made with JavaFX, Kotlin,
            and other tools to create a multiplayer, 1v1 experience.
            You're likely not viewing the completed game because there
            is a lot to create here. come back soon if you are feeling a bit underwhelmed,
            and report any bugs if you come across them!
        """.trimIndent()
        dialog.showAndWait();

        val boardPaneController = runFxmlElement<BoardController>(this);
        val piecePaneController = runFxmlElement<PiecePaneController>(this) {
            piecePane.maxWidthProperty().bind(boardPaneController.boardPane.widthProperty().subtract(10))
            piecePane.maxHeightProperty().bind(boardPaneController.boardPane.heightProperty().subtract(10))

        }


        backgroundPane.prefWidthProperty().bind(scene.widthProperty())
        scene.widthProperty().addListener { obs ->
            println(backgroundPane.width)
        };

        val board = Board();
        val boardLinker = BoardLinker(boardPaneController, board, boardPaneController.boardPane);
        boardLinker.link(Team.BLACK.ordinal)

        board.printAllContents();

        backgroundPane.children.addAll(boardPaneController.boardPane, piecePaneController.piecePane);


    }


}