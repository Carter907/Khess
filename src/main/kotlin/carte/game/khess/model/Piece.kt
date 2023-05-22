package carte.game.khess.model

data class Piece(
    val rank: Int = -1,
    val file: Int = -1,
    val type: Type = Type.PAWN,
    val team: Team = Team.BLACK
) {

    constructor(rank: Int, file: Int, fenChar: Char) : this(
        rank = rank, file = file,
        when (fenChar.toLowerCase()) {
            'r' -> Type.ROOK
            'b' -> Type.BISHOP
            'k' -> Type.KING
            'n' -> Type.KNIGHT
            'q' -> Type.QUEEN
            'p' -> Type.PAWN
            else -> error("not a valid fen character. \"$fenChar\" does not map to a piece")
        }, if (fenChar.isUpperCase()) {
            Team.WHITE
        } else {
            Team.BLACK
        }
    )
}
