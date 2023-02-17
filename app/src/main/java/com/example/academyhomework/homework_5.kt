package com.example.academyhomework

//задание 5.3
data class Player(
    val name: String,
    var rank: Int,
    var win: Int,
    var lose: Int
)

data class Point(
    val x: Int,
    val y: Int
)

enum class PieceType(
    val nameRu: String,
    val notation: String,
) {
    PAWN("пешка", ""),
    KNIGHT("конь", "N"),
    BISHOP("слон", "B"),
    ROOK("ладья", "R"),
    QUEEN("ферзь", "Q"),
    KING("король", "K")
}

enum class PieceColor(val text: String) {
    WHITE("white"),
    BLACK("black")
}

data class Piece(
    val type: PieceType,
    val color: PieceColor
)

object Board {
    val pieces = mutableMapOf<Point, Piece>()
}

interface ChessMove {
    fun pieceAt(square: Board)
    fun movePiece(from: Board, to: Board)
}


fun main() {

}