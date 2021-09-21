package com.kata.tictactoe.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kata.tictactoe.manager.TicTacToeManager

class TicTacToeViewModel : ViewModel() {

     var currentPlayerTurn = TicTacToeManager.PlayerTurn.X

    private val ticTacToeManagerCallback = object : TicTacToeManager.TicTacToeManagerCallback {
        override fun onPlayerTurnChange(playerTurn: TicTacToeManager.PlayerTurn) {
            currentPlayerTurn = playerTurn
        }

        override fun onGameWin(playerTurn: TicTacToeManager.PlayerTurn) {
            Log.d("TTT", "on player win")
        }

    }

    private val manager = TicTacToeManager(ticTacToeManagerCallback)

    fun reset() = manager.reset()

    fun addPoint(x: Int, y: Int) = manager.addPawn(x, y)


}