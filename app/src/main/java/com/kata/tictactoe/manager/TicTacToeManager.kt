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

    init {
        // on init, notify callback the player is set to X by default
        ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.X)
    }

    /**k
     *
     * @param x : x axis on the boardk
     * @param y : y axis on the board
     * @return success : if TRUE, the pawn was added.  if FALSE, an error occured
     */
    fun addPawn(x: Int, y: Int): Boolean {

        // return false if the game is already finished
        if (isGameOver) return false

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

                val win = checkForGameWin(x, y)

                if (win) {
                    finishGame()
                    return true
                }
                // alternate turn
                playerTurn = PlayerTurn.O
                // notify callback player changed
                ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.O)
            }
            PlayerTurn.O -> {
                // change the status of the square to the current player
                board!!.grid[x][y].state = Square.STATE.O


                if (checkForGameWin(x, y)) {
                    finishGame()
                    return true
                }
                // alternate turn
                playerTurn = PlayerTurn.X
                // notify callback player changed
                ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.X)
            }
        }
        return true
    }

    private fun checkForGameWin(x: Int, y: Int): Boolean {

        // same as the current player turn
        val statusToCheckFor = if (playerTurn == PlayerTurn.X) Square.STATE.X else Square.STATE.O

        var count = 1
        //horizontal left
        count = checkNeighborPawns(count, x, y, -1, 0, statusToCheckFor)
        //horizontal right
        count = checkNeighborPawns(count, x, y, 1, 0, statusToCheckFor)

        if (count >= board!!.size) return true

        count = 1

        //vertical down
        count = checkNeighborPawns(count, x, y, 0, 1, statusToCheckFor)
        //vertical up
        count = checkNeighborPawns(count, x, y, 0, -1, statusToCheckFor)

        if (count >= board!!.size) return true

        count = 1

        //diagonal top left
        count = checkNeighborPawns(count, x, y, -1, -1, statusToCheckFor)
        //diagonal bottom right
        count = checkNeighborPawns(count, x, y, 1, 1, statusToCheckFor)


        if (count >= board!!.size) return true

        count = 1

        //diagonal bottom left
        count = checkNeighborPawns(count, x, y, -1, 1, statusToCheckFor)

        //diagonal top right
        count = checkNeighborPawns(count, x, y, 1, -1, statusToCheckFor)

        if (count >= board!!.size) return true

        return false
    }

    private fun checkNeighborPawns(
        count: Int,
        x: Int,
        y: Int,
        xOffset: Int,
        yOffset: Int,
        statusToCheckFor: Square.STATE
    ): Int {
        var updatedCount = count
        var updatedXOffset = xOffset
        var updatedYOffset = yOffset
        while (checkStreak(statusToCheckFor, x + updatedXOffset, y + updatedYOffset)) {
            updatedCount++
            updatedXOffset += xOffset
            updatedYOffset += yOffset
        }
        return updatedCount
    }

    private fun checkStreak(statusToCheckFor: Square.STATE, x: Int, y: Int): Boolean {
        //check bounds
        return x >= 0
                && y >= 0
                && x < board!!.size
                && y < board!!.size
                && board!!.grid[x][y].state == statusToCheckFor


    }

    fun reset(): Board {
        // destroy board
        board = null
        // reset player turn to X
        playerTurn = PlayerTurn.X
        // regenerate board
        board = Board(Constants.BOARD_SIZE)
        // reset game
        isGameOver = false
        // notify callback the player is reset to X by default
        ticTacToeManagerCallback?.onPlayerTurnChange(PlayerTurn.X)
        return board!!
    }

    private fun finishGame() {
        isGameOver = true
        ticTacToeManagerCallback?.onGameWin(playerTurn)
    }


}