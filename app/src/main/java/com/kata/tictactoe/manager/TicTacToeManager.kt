package com.kata.tictactoe.manager

import com.kata.tictactoe.Constants
import com.kata.tictactoe.model.Board
import com.kata.tictactoe.model.Square

class TicTacToeManager(
    private val ticTacToeManagerCallback: TicTacToeManagerCallback?,
    var board: Board? = Board(Constants.BOARD_SIZE),
    var isGameOver: Boolean = false
) {


    interface TicTacToeManagerCallback {
        fun onPlayerTurnChange(playerTurn: PlayerTurn)
        fun onGameWin(playerTurn: PlayerTurn)
    }

    enum class PlayerTurn {
        X,
        O
    }

    // turns start with X by default
    var playerTurn = PlayerTurn.X

    fun reset(): Board {
        // destroy board
        board = null
        // reset player turn to X
        playerTurn = PlayerTurn.X
        // regenerate board
        board = Board(Constants.BOARD_SIZE)
        return board!!
    }

    /**k
     *
     * @param x : x axis on the board
     * @param y : y axis on the board
     * @return success : if TRUE, the pawn was added.  if FALSE, an error occured
     */
    fun addPawn(x: Int, y: Int): Boolean {

        // return false if the board is null
        if (board == null) return false

        // return false if the indexes are out of bounds
        if (x >= board!!.grid.size
            || y >= board!!.grid[y].size
        ) return false

        // return false if the status of the square is not BLANK
        if (board!!.grid[x][y].state != Square.STATE.BLANK)
            return false


        when (playerTurn) {
            PlayerTurn.X -> {
                // change the status of the square to the current player
                board!!.grid[x][y].state = Square.STATE.X
                // alternate turn
                playerTurn = PlayerTurn.O
                // notify callback player changed
                ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.O)
            }
            PlayerTurn.O -> {
                // change the status of the square to the current player
                board!!.grid[x][y].state = Square.STATE.O
                // alternate turn
                playerTurn = PlayerTurn.X
                // notify callback player changed
                ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.X)
            }
        }
        return true
    }

    fun checkForGameWin(): Boolean {
        return false
    }


}