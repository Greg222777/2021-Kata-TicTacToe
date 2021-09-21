package com.kata.tictactoe.activity

import androidx.lifecycle.ViewModel
import com.kata.tictactoe.manager.TicTacToeManager

class TicTacToeViewModel : ViewModel() {

    private val ticTacToeManagerCallback = object : TicTacToeManager.TicTacToeManagerCallback{
        override fun onPlayerTurnChange(playerTurn: TicTacToeManager.PlayerTurn) {
            TODO("Not yet implemented")
        }

        override fun onGameWin(playerTurn: TicTacToeManager.PlayerTurn) {
            TODO("Not yet implemented")
        }

    }

    private val manager = TicTacToeManager(ticTacToeManagerCallback)

    fun reset() = manager.reset()


}