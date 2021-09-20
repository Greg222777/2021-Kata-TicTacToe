package com.kata.tictactoe.model

data class Board(
    val size: Int,
    val grid: List<List<Square>> = List(size) {
        List(size) {
            Square()
        }
    }
)