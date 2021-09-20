package com.kata.tictactoe

import com.kata.tictactoe.manager.TicTacToeManager
import org.junit.Assert
import org.junit.Test
import org.mockito.Matchers.any
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

    // center, left, then right
    private val testCaseCenterHorizontal =
        listOf(Pair(1, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

    /**
    YXY
    -X-
    ---
     **/

    // up to down
    private val testCaseLinearVertical =
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

    // center, up then down
    private val testCaseCenterVertical =
        listOf(Pair(1, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

    /**
    XYY
    -X-
    --X
     **/

    // up to down
    private val testCaseLinearDiagonal =
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

    // center, up then down
    private val testCaseCenterDiagonal =
        listOf(Pair(1, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(2, 0))

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
        listOf(Pair(1, 1), Pair(0, 0), Pair(0, 2), Pair(1, 0), Pair(2, 0), Pair(0, 2))

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

        val mockitest: TicTacToeManager.TicTacToeManagerCallback =
            mock(TicTacToeManager.TicTacToeManagerCallback::class.java)

        val callBack = mockCallback()

        val manager = TicTacToeManager(callBack)

        for (i in testCase.indices - 1) {
            Assert.assertTrue(manager.addPawn(testCase[i].first, testCase[i].second))
        }

        verify(mockitest, Mockito.never()).onGameWin(any())

        Assert.assertTrue(manager.addPawn(testCase.last().first, testCase.last().second))

        verify(callBack, Mockito.times(1)).onGameWin(winingPlayer)
    }

    private fun mockCallback(): TicTacToeManager.TicTacToeManagerCallback {
        return object : TicTacToeManager.TicTacToeManagerCallback {
            override fun onPlayerTurnChange(playerTurn: TicTacToeManager.PlayerTurn) {}
            override fun onGameWin(playerTurn: TicTacToeManager.PlayerTurn) {}
        }
    }
}