package com.kata.tictactoe.manager

import com.kata.tictactoe.Constants
import com.kata.tictactoe.model.Board

class TicTacToeManager() {
    var board: Board? = null

    fun generateBoard(): Board {
        board = Board(Constants.BOARD_SIZE)
        return board!!
    }

    fun reset(): Board {
        board = null
        generateBoard()
        return board!!
    }


}