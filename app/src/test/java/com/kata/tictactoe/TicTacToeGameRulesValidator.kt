package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class TicTacToeGameRulesValidator {

    /**
     * TEST CASES
     *
     * each index alternates player (starts with X)
     * fifth turn should be a win for the player X
     *
     */


    /***
    XXX
    YY-
    ---
     **/

    // left to right
    private val testCaseLinearHorizontal =
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

    // left, right then center
    private val testCaseCenterHorizontal =
        listOf(Pair(0, 0), Pair(0, 1), Pair(2, 0), Pair(1, 1), Pair(1, 0))

    /**
    YXY
    -X-
    -X-
     **/

    // up to down
    private val testCaseLinearVertical =
        listOf(Pair(1, 0), Pair(0, 1), Pair(1, 1), Pair(2, 0), Pair(1, 2))

    // up, down then center
    private val testCaseCenterVertical =
        listOf(Pair(1, 0), Pair(0, 0), Pair(1, 2), Pair(2, 0), Pair(1, 1))

    /**
    XYY
    -X-
    --X
     **/

    // top left to bottom right
    private val testCaseLinearDiagonal =
        listOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(2, 0), Pair(2, 2))

    // top left, bottom right then center
    private val testCaseCenterDiagonal =
        listOf(Pair(0, 0), Pair(1, 0), Pair(2, 2), Pair(2, 0), Pair(1, 1))

    /**
     * PLAYER O WIN
     *
     * check that player O can also win
     * sixth turn should be a win for player O
     */

    /**
    YYY
    -X-
    X-X
     **/

    //from left to right
    private val testCasePlayerOWins =
        listOf(Pair(1, 1), Pair(0, 0), Pair(0, 2), Pair(1, 0), Pair(2, 2), Pair(2, 0))

    @Test
    fun checkAllWinCases() {
        simulateWinCase(testCaseLinearHorizontal, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCaseCenterHorizontal, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCaseLinearVertical, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCaseCenterVertical, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCaseLinearDiagonal, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCaseCenterDiagonal, TicTacToeManager.PlayerTurn.X)

        simulateWinCase(testCasePlayerOWins, TicTacToeManager.PlayerTurn.O)

    }


    private fun simulateWinCase(
        testCase: List<Pair<Int, Int>>,
        winingPlayer: TicTacToeManager.PlayerTurn
    ) {

        val mockCallback: TicTacToeManager.TicTacToeManagerCallback =
            mock(TicTacToeManager.TicTacToeManagerCallback::class.java)


        val manager = TicTacToeManager(mockCallback)

        for (i in 0..testCase.size - 2) {
            Assert.assertTrue(manager.addPawn(testCase[i].first, testCase[i].second))
        }

        // verify the game hasn't been won
        verify(mockCallback, Mockito.never()).onGameWin(TicTacToeManager.PlayerTurn.X)

        // winning pawn
        Assert.assertTrue(
            manager.addPawn(
                testCase[testCase.size - 1].first,
                testCase[testCase.size - 1].second
            )
        )

        // onGameWin() must have been called once after the wining pawn with the correct player
        verify(mockCallback, Mockito.times(1)).onGameWin(winingPlayer)
    }

}