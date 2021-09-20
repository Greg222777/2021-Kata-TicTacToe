package com.kata.tictactoe.model

data class Square(var state: STATE = STATE.BLANK) {
    enum class STATE {
        BLANK,
        X,
        O
    }
}