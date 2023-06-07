package carte.game.khess

import carte.game.khess.controllers.PieceController
import carte.game.khess.controllers.SquareController
import carte.game.khess.model.*
import carte.toolfx.core.Controller
import carte.toolfx.core.runFxmlElement
import javafx.scene.image.Image
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
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

    fun link(
        playingAs: Int = Team.WHITE.ordinal,
        startPositionFen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    ) {
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
                    addSquareAndPiece(entry.key, entry.value)
                }
            }
        }
    }
    private fun addSquareAndPiece(currentSquare: Square, currentPiece: Piece?) {
        val file: Int;
        val rank: Int;

        if (boardOrientation == Team.BLACK.ordinal) {
            rank = currentSquare.rank
            file = abs(currentSquare.file - 9)

        } else {
            rank = abs(currentSquare.rank - 9)
            file = currentSquare.file


        }
        grid.add(
            runFxmlElement<SquareController>(context) {
                colorRoot.minWidth = board.squareSize;
                colorRoot.minHeight = board.squareSize;
                colorRoot.background = Background(
                    BackgroundFill(
                        setSquareColor(currentSquare.color),
                        null, null,
                    )
                )


            }.squareRoot.apply {

                children.add(
                    runFxmlElement<PieceController>(context) {

                        currentPiece?.apply {

                            setPieceImage(team, type)
                        }


                    }.pieceImage
                )


            },
            file,
            rank
        )
    }

    private fun setSquareColor(color: Int): Paint =
        Color.web(
            when (color) {
                1 -> board.darkSquareColor;
                0 -> board.lightSquareColor;
                else -> error("no color")
            }
        )




    private fun PieceController.setPieceImage(team: Team, type: Type) {
        val path =
            "/sets/set_0/${team.name.toLowerCase()}_${type.name.toLowerCase()}.png";
        pieceImage.image =
            Image(
                javaClass.getResource(path)
                    ?.toExternalForm() ?: error("could not locate piece image ($path)")
            )
    }

    private fun resetBoardPane() {
        grid.children.clear();

    }

    /**
     * Used to flip the board by setting boardOrientation to be opposite of its current value
     */
    fun flipBoardPane() {
        resetBoardPane();

        boardOrientation = abs(boardOrientation - 1)

        addContents();
    }
}