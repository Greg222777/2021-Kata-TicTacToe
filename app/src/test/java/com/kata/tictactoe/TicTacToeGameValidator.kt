package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import com.kata.tictactoe.model.Square
import org.junit.Assert
import org.junit.Test

class TicTacToeGameValidator {


    @Test
    fun checkSquareStatusChange() {
        val manager = TicTacToeManager(null)

        Assert.assertNotNull(manager.board)

        // check adding a first pawn to a blank square
        val success = manager.addPawn(0, 0)
        // function return success
        Assert.assertTrue(success)
        // first turn so the square must countain an X
        Assert.assertEquals(manager.board!!.grid[0][0].state, Square.STATE.X)

    }

    @Test
    fun checkPlayerTurnChange() {
        val manager = TicTacToeManager(null)

        // fill the first line with alternate X and Y
        for (turn in 0 until Constants.BOARD_SIZE) {

            // check that the turn is X for evens turns, Y for odds
            val expectedPlayerTurn =
                if (turn % 2 == 0) TicTacToeManager.PlayerTurn.X else TicTacToeManager.PlayerTurn.O
            Assert.assertEquals(
                manager.playerTurn,
                expectedPlayerTurn
            )

            // add a pawn to go to alternate player
            val success = manager.addPawn(0, turn)
            // check that the pawn was correctly added
            Assert.assertTrue(success)

        }

    }

    @Test
    fun checkPlayerTurnChangeFailure() {
        val manager = TicTacToeManager(null)

        // check that the turn doesn't change if adding a pawn results in a failure

        // if the status of the square is not BLANK
        Assert.assertTrue(manager.addPawn(0, 0)) // player turn should now be Y
        Assert.assertNotEquals(manager.board!!.grid[0][0].state, Square.STATE.BLANK)

        // try to add a pawn in the same position
        Assert.assertFalse(manager.addPawn(0, 0))
        // player turn should remain Y
        Assert.assertEquals(manager.playerTurn, TicTacToeManager.PlayerTurn.O)

        // try to add a pawn out of bonds
        Assert.assertFalse(manager.addPawn(Constants.BOARD_SIZE + 1, Constants.BOARD_SIZE + 1))
        // player turn should remain Y
        Assert.assertEquals(manager.playerTurn, TicTacToeManager.PlayerTurn.O)
    }

}