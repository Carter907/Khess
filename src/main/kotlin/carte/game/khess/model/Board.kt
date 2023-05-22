package carte.game.khess.model

import java.util.function.Consumer
import kotlin.math.abs
import kotlin.math.absoluteValue


/**
 * Class used to store information about the Board of the game
 * should only hold the state of the game and nothing more
 * */
data class Board(
    val darkSquareColor: String = "#827a65",
    val lightSquareColor: String = "#ebe0c5",
    val squareSize: Double = 75.0,
) {

    /**
     * Matrix representation of the board.
     *
     */
    val squareMat: Array<Array<Square>> = Array(8) { Array(8) { Square() } }
    val pieceMat: Array<Array<Piece?>> = Array(8) { Array(8) { null } }
    lateinit var pieceSquareMat: Array<Array<Map<Square, Piece?>>>;

    /**
     * Constructs the board based on the index of the square in the matrix
     *
     */
    private fun constructSquares() {
        for (r in squareMat.indices) {
            for (f in squareMat[r].indices) {

                squareMat[r][f] = Square(
                    color =
                    if ((r + f) % 2 == 0) {
                        0
                    } else {
                        1
                    },
                    rank = abs(r - 8),
                    file = f + 1

                )
            }
        }
    }
    private fun applyToPairsMat(consumer: (Map<Square, Piece?>) -> Unit) {
        for (row in pieceSquareMat) {
            row.forEach(consumer);
        }
    }
    private fun applyToPieceMat(consumer: (Piece?) -> Unit) {
        for (row in pieceMat) {
            row.forEach(consumer);
        }
    }
    private fun applyToSquareMat(consumer: (Square) -> Unit) {
        for (row in squareMat) {
            row.forEach(consumer);
        }
    }


    fun flipBoard() {


    }


    private fun addPieces(fen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR") {
        var file = 1;
        var rank = 8;

        var i = 0;
        while (i < fen.length) {

            if (fen[i] == '/') {
                rank--;
                file = 1;

            }
            else if (fen[i].isDigit()) {
                file+=fen[i].toInt();

            } else {



                pieceMat[abs(rank-8)][file-1] = Piece(rank, file, fen[i]);

                file++;

            }
            i++;



        }






    }

    /**
     * method used to start a boards contents to the initial start position playing as black
     *
     */

    fun initializeToStartPosition() {
        constructSquares();
        addPieces();
        pieceSquareMat =
            Array(8) { r ->
                Array(8) { f ->
                    mapOf(Pair(squareMat[r][f], pieceMat[r][f]))
                }
            }
    }

    /**
     * used to print visualize the matrix created by the this class.
     *
     */

    fun printAllContents() {

        println("piece mat:")
        for (r in pieceSquareMat.indices) {
            for (map in pieceSquareMat[r]) {
                map.entries.iterator().next().apply {
                    print("[${value?.file},${value?.rank}]")
                }


            }
            println();
        }
        println("square mat:")
        for (r in pieceSquareMat.indices) {
            for (map in pieceSquareMat[r]) {
                map.entries.iterator().next().apply {
                    print("[${key.file},${key.rank}]")
                }


            }
            println();
        }
    }

}