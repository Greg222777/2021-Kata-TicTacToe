package com.kata.tictactoe.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kata.tictactoe.R
import com.kata.tictactoe.manager.TicTacToeManager

class TicTacToeViewModel : ViewModel() {

    var currentPlayerTurn = TicTacToeManager.PlayerTurn.X
    val infoTextLiveData = MutableLiveData(R.string.player_x_turn)

    private val ticTacToeManagerCallback = object : TicTacToeManager.TicTacToeManagerCallback {
        override fun onPlayerTurnChange(playerTurn: TicTacToeManager.PlayerTurn) {
            currentPlayerTurn = playerTurn
            val resId = when(playerTurn){
                TicTacToeManager.PlayerTurn.X -> R.string.player_x_turn
                TicTacToeManager.PlayerTurn.O -> R.string.player_o_turn
            }
            infoTextLiveData.postValue(resId)
        }

        override fun onGameWin(playerTurn: TicTacToeManager.PlayerTurn) {
            val resId = when(playerTurn){
                TicTacToeManager.PlayerTurn.X -> R.string.player_x_win
                TicTacToeManager.PlayerTurn.O -> R.string.player_o_win
            }
            infoTextLiveData.postValue(resId)
        }

    }

    private val manager = TicTacToeManager(ticTacToeManagerCallback)

    fun reset() = manager.reset()

    fun addPoint(x: Int, y: Int) = manager.addPawn(x, y)


}