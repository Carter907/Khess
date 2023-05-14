package carte.game.khess.model


/**
 * Class used to store information about the Board of the game
 * should only hold the state of the game and nothing more
 * */
data class Board(
    val darkSquareColor: String = "#615944",
    val lightSquareColor: String = "#ebe0c5",
    val squareSize: Double = 75.0,
) {

    /**
     * Matrix representation of the board.
     *
     */
    val squareMat: Array<Array<Square>> = Array(8) { Array(8) { Square() } }
    val pieceMat: Array<Array<Piece?>> = Array(8) { Array(8) { null } }
    lateinit var pieceSquareMapping: Array<Array<Map<Square, Piece?>>>;

    /**
     * Constructs the board based on the index of the square in the matrix
     *
     */
    fun constructSquares() {
        for (r in squareMat.indices) {
            for (f in squareMat[r].indices) {

                squareMat[r][f] = Square(
                    color =
                    if ((r + f) % 2 == 0) {
                        1
                    } else {
                        0
                    },
                    rank = r + 1,
                    file = f + 1

                )
            }
        }
    }


    fun addPieces(fen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR") {
        var ch: Char
        var rank = 8
        var file = 1
        var i = 0;
        while (i in fen.indices) {
            ch = fen[i]
            if (ch == '/') {
                rank--
                file = 0
                i++
                file++
                continue
            } else if (Character.isDigit(ch)) {
                file += (ch.toString() + "").toInt() - 1
                i++
                file++
                continue
            }
            pieceMat[rank - 1][file - 1] = Piece(rank, file, ch);

            i++
            file++
        }

    }

    fun initializeToStartPosition() {
        constructSquares();
        addPieces();
        pieceSquareMapping =
            Array(8) { r ->
                Array(8) { f ->
                    mapOf(Pair(squareMat[r][f], pieceMat[r][f]))
                }
            }
    }

    fun printAllContents() {

        println("Both contents:")
        for (r in pieceSquareMapping.indices) {
            for (pair in pieceSquareMapping[r]) {
                print(pair)

            }
            println();
        }


    }

}