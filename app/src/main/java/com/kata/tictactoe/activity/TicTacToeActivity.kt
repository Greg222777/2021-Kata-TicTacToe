package com.kata.tictactoe.activity

import android.app.Activity
import android.os.Bundle
import com.kata.tictactoe.databinding.TicTacToeActivityBinding


class TicTacToeActivity : Activity() {
    private var binding: TicTacToeActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicTacToeActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}