package carte.game.khess

import carte.game.khess.controllers.SquareController
import carte.toolfx.core.Controller
import carte.toolfx.core.runFxmlElement
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

/**
 * Links the Board UI to the Board class
 *
 * */
class BoardLinker(private val context: Controller, private val board: Board, private val grid: GridPane) {

    /**
     * does some work on the Board object.
     * constructs the Board object to fill its matrix
     * maps each square present in the matrix with it's corresponding node of the grid UI.
     * */

    fun link() {
        board.construct()
        grid.maxWidth = board.squareSize * 8;
        grid.maxHeight = board.squareSize * 8;
        board.mat.forEach {
            it.forEach { sqr ->
                grid.add(
                    runFxmlElement<SquareController>(context) {
                        squareRoot.minWidth = board.squareSize;
                        squareRoot.minHeight = board.squareSize;
                        squareRoot.background = Background(
                            BackgroundFill(Color.web(
                                when (sqr.color) {
                                    1 -> board.darkSquareColor;
                                    0 -> board.lightSquareColor;
                                    else -> error("no color")
                                }
                            ), null, null,))
                    }.squareRoot,
                    sqr.file,
                    sqr.rank,
                )


            }

        }

    }
}