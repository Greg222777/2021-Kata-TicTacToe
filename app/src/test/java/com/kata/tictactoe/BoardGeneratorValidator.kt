package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import com.kata.tictactoe.model.Square
import org.junit.Assert
import org.junit.Test

class BoardGeneratorValidator {

    private val manager = TicTacToeManager()

    @Test
    fun checkBoardSize() {

        Assert.assertNotNull(manager.board)

        // check that x axis has a correct size
        Assert.assertEquals(Constants.BOARD_SIZE, manager.board!!.grid.size)

        // check that each Y axis has a correct size
        for (column in manager.board!!.grid) {
            Assert.assertEquals(Constants.BOARD_SIZE, column.size)
        }
    }

    @Test
    fun checkSquaresStatus() {

        Assert.assertNotNull(manager.board)

        // check the status of each square (must be not null and BLANK by default)
        for (line in manager.board!!.grid) {
            for (square in line) {
                Assert.assertNotNull(square)
                Assert.assertEquals(square.state, Square.STATE.BLANK)
            }
        }
    }

    @Test
    fun checkBoardReset(){
        //TODO after adding square status change feature
    }

}