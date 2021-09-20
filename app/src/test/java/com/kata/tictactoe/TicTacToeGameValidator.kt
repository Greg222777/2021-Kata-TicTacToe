package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import com.kata.tictactoe.model.Square
import org.junit.Assert
import org.junit.Test

class TicTacToeGameValidator {


    @Test
    fun checkSquareStatusChange() {
        val manager = TicTacToeManager()

        Assert.assertNotNull(manager.board)

        // check adding a first pawn to a blank square
        val success = manager.addPawn(0, 0)
        Assert.assertEquals(manager.board!!.grid[0][0], Square.STATE.X)
        // function return success
        Assert.assertTrue(success)
    }

    @Test
    fun checkPlayerTurnChange() {
        val manager = TicTacToeManager()

        // fill the first line with alternate X and Y
        for (turn in 0..Constants.BOARD_SIZE) {

            val success = manager.addPawn(0, turn)
            // check that the pawn was correctly added
            Assert.assertTrue(success)

            // check that the turn is X for evens turns, Y for odds
            val expectedPlayerTurn =
                if (turn % 2 == 0) TicTacToeManager.PlayerTurn.X else TicTacToeManager.PlayerTurn.Y
            Assert.assertEquals(
                manager.playerTurn,
                expectedPlayerTurn
            )

        }

    }

    @Test
    fun checkPlayerTurnChangeFailure(){
        val manager = TicTacToeManager()
        // check that the turn doesn't change if adding a pawn was a failure

        // if the status of the square is not BLANK
        Assert.assertTrue(manager.addPawn(0,0)) // player turn should now be Y
        Assert.assertNotEquals(manager.board!!.grid[0][0], Square.STATE.BLANK)

        // try to add a pawn in the same position
        Assert.assertFalse(manager.addPawn(0, 0))
        // player turn should remain Y
        Assert.assertEquals(manager.playerTurn, TicTacToeManager.PlayerTurn.Y)

        // try to add a pawn out of bonds
        Assert.assertFalse(manager.addPawn(Constants.BOARD_SIZE + 1, Constants.BOARD_SIZE + 1))
        // player turn should remain Y
        Assert.assertEquals(manager.playerTurn, TicTacToeManager.PlayerTurn.Y)
    }

}