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
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane


@Screen("/fxml/screens/home.fxml")
class HomeController : Controller() {

    @FXML
    lateinit var backgroundPane: StackPane;

    @FXML
    lateinit var flipBoardIcon: ImageView;

    override fun onCreate() {



        val boardPaneController = runFxmlElement<BoardController>(this);
        val piecePaneController = runFxmlElement<PiecePaneController>(this) {
            piecePane.maxWidthProperty().bind(boardPaneController.boardPane.widthProperty().subtract(10))
            piecePane.maxHeightProperty().bind(boardPaneController.boardPane.heightProperty().subtract(10))

        }
        
        backgroundPane.prefWidthProperty().bind(scene.widthProperty())


        val board = Board();
        val boardLinker = BoardLinker(boardPaneController, board, boardPaneController.boardPane);
        boardLinker.link(Team.BLACK.ordinal)

        board.printAllContents();

        val image = Image(javaClass.getResource("/icons/flip_board.png").toExternalForm())
        flipBoardIcon.image = image;

        flipBoardIcon.setOnMouseClicked {
            boardLinker.flipBoard();
        }

        backgroundPane.children.addAll(boardPaneController.boardPane, piecePaneController.piecePane);




    }


}