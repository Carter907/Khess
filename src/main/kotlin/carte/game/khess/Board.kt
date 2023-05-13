package carte.game.khess
/**
* Class used to store information about the Board of the game
*
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
    val mat: Array<Array<Square>> = Array(8) { Array(8) { Square() } };

    /**
     * Constructs the board based on the index of the square in the matrix
     *
     */
    fun construct() {
        for (r in mat.indices) {
            for (f in mat[r].indices) {

                mat[r][f] = Square(
                    color =
                    if ((r + f) % 2 == 0) {
                        1
                    } else {
                        0
                    },
                    rank = r+1,
                    file = f+1

                )
            }
        }
    }
    /**
    * prints the contents of the board matrix
    *
    * */
    fun printContents() = mat.forEach {
        it.forEach { sqr ->
            print(sqr)
            if (sqr.file % 8 == 0)
                println()
        }
    };

}