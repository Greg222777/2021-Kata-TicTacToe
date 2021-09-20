package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import com.kata.tictactoe.model.Board
import com.kata.tictactoe.model.Square
import org.junit.Assert
import org.junit.Test

class BoardGeneratorValidator {

    private val ticTacToeManager = TicTacToeManager()
    private var board : Board? = ticTacToeManager.generateBoard()

    @Test
    fun checkBoardSize() {

        // check that x axis has a correct size
        Assert.assertEquals(Constants.BOARD_SIZE, board!!.grid.size)

        // check that each Y axis has a correct size
        for (column in board!!.grid) {
            Assert.assertEquals(Constants.BOARD_SIZE, column.size)
        }
    }

    @Test
    fun checkSquaresStatus() {

        Assert.assertNotNull(board)

        // check the status of each square (must be not null and BLANK by default)
        for (line in board!!.grid) {
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