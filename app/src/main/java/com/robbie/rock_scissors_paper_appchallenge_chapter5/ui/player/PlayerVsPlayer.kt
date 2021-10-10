package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.ActivityPlayerVsPlayerBinding
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.instruction.PageIntro
import com.robbie.rock_scissors_paper_appchallenge_chapter5.utils.Helper

class PlayerVsPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerVsPlayerBinding

    private var elementPlayer1 = arrayListOf<LinearLayout>()
    private var elementPlayer2 = arrayListOf<LinearLayout>()


    private var player1 = 0
    private var player2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerVsPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListenersPlayer()


    }

    private fun initListenersPlayer() {
        elementPlayer1 = arrayListOf(
            binding.llRockPlayer1,
            binding.llScissorsPlayer1,
            binding.llPaperPlayer1)
        elementPlayer1.forEachIndexed { index, linearLayout ->
            linearLayout.setOnClickListener { elem ->
                player1 = index
                elementPlayer1.forEach {
                    if (elem != it){
                        Helper.defSetBg(it)
                    }
                }
            }
        }


        elementPlayer2 = arrayListOf(
            binding.llRockPlayer2,
            binding.llScissorsPlayer2,
            binding.llPaperPlayer2)
        elementPlayer2.forEachIndexed { index, linearLayout ->
            linearLayout.setOnClickListener { elem ->
                player2 = index
                elementPlayer2.forEach {

                    if (elem != it){
                        Helper.defSetBg(it)
                    }
                }
                resultPlayerVsPlayer()

            }
        }

    }

    private fun distClick() {
        binding.ivReset.setOnClickListener {
            resetGame()
        }
        elementPlayer1.forEachIndexed { _, linearLayout ->
            linearLayout.isClickable = false
        }
        elementPlayer2.forEachIndexed { _, linearLayout ->
            linearLayout.isClickable = false
        }

    }


    private fun resultPlayerVsPlayer() {

        when {
            (player1 + 1) % 3 == player2 -> {
                Helper.setBg(elementPlayer1[player1])
                Helper.setBg(elementPlayer2[player2])
                Helper.showToast(this, "Player 1 Wins")
                Helper.showDialog(this, R.drawable.p1win, "close")
            }
            player1 == player2 -> {
                Helper.setBg(elementPlayer1[player1])
                Helper.setBg(elementPlayer2[player2])
                Helper.showToast(this, "Draw")
                Helper.showDialog(this, R.drawable.draw, "close")
            }
            else -> {
                Helper.setBg(elementPlayer1[player1])
                Helper.setBg(elementPlayer2[player2])
                Helper.showToast(this, "Player 2 Wins")
                Helper.showDialog(this, R.drawable.p2win, "close")
            }

        }
        distClick()
        binding.ivReset.setOnClickListener {
            resetGame()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.menu_home -> {
                startActivity(Intent(this, PageIntro::class.java))
                true

            }else ->{
                true
            }
        }
    }

    private fun resetGame() {
        initListenersPlayer()

        player1 = 0
        elementPlayer1.forEach {
            Helper.defSetBg(it)
            it.isClickable = true
        }

        elementPlayer2.forEach {
            Helper.defSetBg(it)

        }

    }


}