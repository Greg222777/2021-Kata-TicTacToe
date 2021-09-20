package com.kata.tictactoe.manager

import com.kata.tictactoe.Constants
import com.kata.tictactoe.model.Board
import com.kata.tictactoe.model.Square

class TicTacToeManager {

    enum class PlayerTurn {
        X,
        Y
    }

    // game board
    var board: Board? = null

    // turns start with X by default
    var playerTurn = PlayerTurn.X

    fun generateBoard(): Board {
        // init board with size specified in Constants class
        board = Board(Constants.BOARD_SIZE)
        return board!!
    }

    fun reset(): Board {
        // destroy board
        board = null
        // reset player turn to X
        playerTurn = PlayerTurn.X
        // regenerate board
        generateBoard()
        return board!!
    }

    /**k
     *
     * @param x : x axis on the board
     * @param y : y axis on the board
     * @return success : if TRUE, the pawn was added.  if FALSE, an error occured
     */
    fun addPawn(x: Int, y: Int): Boolean {
        return true

    }


}