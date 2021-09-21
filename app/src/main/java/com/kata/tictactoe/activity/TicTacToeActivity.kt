package com.kata.tictactoe.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kata.tictactoe.R
import com.kata.tictactoe.databinding.TicTacToeActivityBinding
import com.kata.tictactoe.manager.TicTacToeManager


class TicTacToeActivity : AppCompatActivity() {
    private var binding: TicTacToeActivityBinding? = null
    lateinit var viewModel: TicTacToeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicTacToeActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TicTacToeViewModel::class.java)
        setContentView(binding!!.root)
    }

    override fun onStart() {
        super.onStart()
        listenForClicks()
    }

    private fun listenForClicks() {

        binding?.apply {
            topLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 0) }
            }
            topCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 0) }
            }
            topRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 0) }
            }
            centerLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 1) }
            }
            centerCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 1) }
            }
            centerRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 1) }
            }
            bottomLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 2) }
            }
            bottomCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 2) }
            }
            bottomRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 2) }
            }

            resetButton.setOnClickListener { viewModel.reset() }
        }
    }

    private fun addPawn(imageView: ImageView, x: Int, y: Int) {
        // retrieve player turn before it is changed
        val currentPlayerTurn = viewModel.currentPlayerTurn
        val success = viewModel.addPoint(x, y)

        if (success) {
            // pawn drawable
            val res = when (currentPlayerTurn) {
                TicTacToeManager.PlayerTurn.X -> ContextCompat.getDrawable(this, R.drawable.pawn_x)
                TicTacToeManager.PlayerTurn.O -> ContextCompat.getDrawable(
                    this,
                    R.drawable.pawn_circle
                )
            }

            Glide.with(this).load(res).into(imageView)


        }
    }
}