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
    private lateinit var viewModel: TicTacToeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicTacToeActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TicTacToeViewModel::class.java)
        observeViewModel()
        setContentView(binding!!.root)
    }

    override fun onStart() {
        super.onStart()
        listenForClicks()
    }

    private fun observeViewModel() {
        viewModel.infoTextLiveData.observe(this, { textResId ->
            binding?.gameInfoTextView?.text = getString(textResId)
        })
    }

    private fun listenForClicks() {

        binding?.let {
            it.topLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 0) }
            }
            it.topCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 0) }
            }
            it.topRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 0) }
            }
            it.centerLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 1) }
            }
            it.centerCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 1) }
            }
            it.centerRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 1) }
            }
            it.bottomLeftImageView.apply {
                setOnClickListener { addPawn(this, 0, 2) }
            }
            it.bottomCenterImageView.apply {
                setOnClickListener { addPawn(this, 1, 2) }
            }
            it.bottomRightImageView.apply {
                setOnClickListener { addPawn(this, 2, 2) }
            }

            it.resetButton.setOnClickListener { reset() }
        }
    }

    private fun reset() {

        binding?.let {
            // clear all imageviews
            Glide.with(this).clear(it.topLeftImageView)
            Glide.with(this).clear(it.topCenterImageView)
            Glide.with(this).clear(it.topRightImageView)
            Glide.with(this).clear(it.centerLeftImageView)
            Glide.with(this).clear(it.centerCenterImageView)
            Glide.with(this).clear(it.centerRightImageView)
            Glide.with(this).clear(it.bottomLeftImageView)
            Glide.with(this).clear(it.bottomCenterImageView)
            Glide.with(this).clear(it.bottomRightImageView)
            // clear game info text view
            it.gameInfoTextView.text = ""
        }
        // reset game logic
        viewModel.reset()

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