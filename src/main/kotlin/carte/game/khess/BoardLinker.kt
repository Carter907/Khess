package carte.game.khess

import carte.game.khess.controllers.PieceController
import carte.game.khess.controllers.SquareController
import carte.game.khess.model.Board
import carte.game.khess.model.Team
import carte.toolfx.core.Controller
import carte.toolfx.core.runFxmlElement
import javafx.scene.image.Image
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import kotlin.math.abs

/**
 * Links the Board UI to the Board class
 *
 * */
class BoardLinker(private val context: Controller, private val board: Board, private val grid: GridPane) {

    private var boardOrientation: Int = Team.WHITE.ordinal;

    /**
     * does some work on the Board object.
     * constructs the Board object to fill its matrix
     * maps each square present in the matrix with it's corresponding node of the grid UI.
     * */

    fun link(playingAs: Int = boardOrientation, startPositionFen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR") {
        boardOrientation = playingAs;
        board.initializeToStartPosition(startPositionFen);
        grid.maxWidth = board.squareSize * 8;
        grid.maxHeight = board.squareSize * 8;
        addContents();


    }

    private fun addContents() {
        board.pieceSquareMat.forEach {
            it.forEach { map ->
                map.entries.forEach { entry ->
                    var file: Int;
                    var rank: Int;

                    if (boardOrientation == Team.BLACK.ordinal) {
                        rank = entry.key.rank
                        file = abs(entry.key.file - 9)

                    } else {
                        rank = abs(entry.key.rank - 9)
                        file = entry.key.file


                    }
                    grid.add(
                        runFxmlElement<SquareController>(context) {
                            colorRoot.minWidth = board.squareSize;
                            colorRoot.minHeight = board.squareSize;
                            colorRoot.background = Background(
                                BackgroundFill(
                                    Color.web(
                                        when (entry.key.color) {
                                            1 -> board.darkSquareColor;
                                            0 -> board.lightSquareColor;
                                            else -> error("no color")
                                        }
                                    ),
                                    null, null,
                                )
                            )


                        }.squareRoot.apply {

                            children.add(
                                runFxmlElement<PieceController>(context) {

                                    entry.value?.apply {
                                        val path =
                                            "/sets/set_0/${team.name.toLowerCase()}_${type.name.toLowerCase()}.png";
                                        pieceImage.image =
                                            Image(
                                                javaClass.getResource(path)
                                                    ?.toExternalForm() ?: error("could not locate piece image ($path)")
                                            )

                                    }


                                }.pieceImage
                            )


                        },
                        file,
                        rank
                    )
                }
            }
        }
    }

    private fun resetBoardPane() {
        grid.children.clear();

    }


    fun flipBoardPane() {
        resetBoardPane();

        boardOrientation = abs(boardOrientation - 1)

        addContents();
    }
}