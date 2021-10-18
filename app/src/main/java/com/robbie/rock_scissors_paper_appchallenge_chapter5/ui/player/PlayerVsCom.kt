package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.robbie.rock_scissors_paper_appchallenge_chapter5.utils.Helper
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.ActivityPlayerVsComBinding
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form.GetName
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.instruction.PageIntro
import com.robbie.rock_scissors_paper_appchallenge_chapter5.utils.Game

open class PlayerVsCom : AppCompatActivity(), Game {
    private lateinit var binding: ActivityPlayerVsComBinding

    private var elementPlayer = arrayListOf<LinearLayout>()
    private var elementCom = arrayListOf<LinearLayout>()

    private var player = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPlayerVsComBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setName()
        initListenersPlayer()
    }
    private fun setName(){
        binding.tvTitlePlayer.text = String.format(
            getString(
                R.string.text_title_player,
                GetName(this).userName
            )

        )
    }

    private fun initListenersPlayer() {

        elementPlayer =
            arrayListOf(binding.llRockPlayer, binding.llScissorsPlayer, binding.llPaperPlayer)
        elementCom =
            arrayListOf(binding.llRockCom, binding.llScissorsCom, binding.llPaperCom)

        elementPlayer.forEachIndexed { index, linearLayout ->
            linearLayout.setOnClickListener { elem ->
                Helper.setBg(elem)
                scaleIvPlayer(elem)
                player = index
                elementPlayer.forEach {
                    if (elem != it) {
                        Helper.defSetBg(it)
                    }
                }
                resultPlayerVsCom()

            }


        }


    }


    override fun resultPlayerVsCom() {
        val computerChoice = (0..2).random()
        scaleIvCom(elementCom[computerChoice])
        Helper.setBg(elementCom[computerChoice])
        when {
            (player + 1) % 3 == computerChoice -> {
                Helper.showToast(this, "You Wins")
                Helper.showDialog(this, R.drawable.you_win, "close")
            }
            player == computerChoice -> {
                Helper.showToast(this, "Draw")
                Helper.showDialog(this, R.drawable.draw, "close")

            }
            else -> {
                Helper.showToast(this, "Computer Wins")
                Helper.showDialog(this, R.drawable.you_lose, "close")

            }


        }
        distClick()

    }


    private fun distClick() {
        binding.ivReset.setOnClickListener{
            resetGame()
        }
        elementPlayer.forEachIndexed { _, linearLayout ->
            linearLayout.isClickable = false
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

        player = 0
        elementPlayer.forEach {
            Helper.defSetBg(it)
            resetChoicePlayer()
            it.isClickable = true
        }

        elementCom.forEach {
            Helper.defSetBg(it)
            resetChoiceCom()

        }

    }

    private fun scaleIvPlayer(args: Any) {
        when {
            args === binding.llRockPlayer-> {
                binding.llRockPlayer.scaleX = "0.85".toFloat()
                binding.llRockPlayer.scaleY = "0.85".toFloat()

                // Button PAPER and ROCK scale reset
                binding.llPaperPlayer.scaleX = "1.2".toFloat()
                binding.llPaperPlayer.scaleY = "1.2".toFloat()
                binding.llScissorsPlayer.scaleX = "1.2".toFloat()
                binding.llScissorsPlayer.scaleY = "1.2".toFloat()

            }
            args === binding.llPaperPlayer -> {
                binding.llPaperPlayer.scaleX = "0.85".toFloat()
                binding.llPaperPlayer.scaleY = "0.85".toFloat()

                // Button ROCK and SCISSORS scale reset
                binding.llRockPlayer.scaleX = "1.2".toFloat()
                binding.llRockPlayer.scaleY = "1.2".toFloat()
                binding.llScissorsPlayer.scaleX = "1.2".toFloat()
                binding.llScissorsPlayer.scaleY = "1.2".toFloat()

            }
            args === binding.llScissorsPlayer -> {
                binding.llScissorsPlayer.scaleX = "0.85".toFloat()
                binding.llScissorsPlayer.scaleY = "0.85".toFloat()

                // Button ROCK and PAPER scale reset
                binding.llRockPlayer.scaleX = "1.2".toFloat()
                binding.llRockPlayer.scaleY = "1.2".toFloat()
                binding.llPaperPlayer.scaleX = "1.2".toFloat()
                binding.llPaperPlayer.scaleY = "1.2".toFloat()
            }
            else -> resetChoicePlayer()
        }
    }

    private fun resetChoicePlayer() {
        binding.llRockPlayer.scaleX = "1.2".toFloat()
        binding.llRockPlayer.scaleY = "1.2".toFloat()
        binding.llPaperPlayer.scaleX = "1.2".toFloat()
        binding.llPaperPlayer.scaleY = "1.2".toFloat()
        binding.llScissorsPlayer.scaleX = "1.2".toFloat()
        binding.llScissorsPlayer.scaleY = "1.2".toFloat()
    }

    private fun scaleIvCom(args: Any) {
        when {
            args === binding.llRockCom -> {
                binding.llRockCom.scaleX = "0.85".toFloat()
                binding.llRockCom.scaleY = "0.85".toFloat()

                // Button PAPER and ROCK scale reset
                binding.llPaperCom.scaleX = "1.2".toFloat()
                binding.llPaperCom.scaleY = "1.2".toFloat()
                binding.llScissorsCom.scaleX = "1.2".toFloat()
                binding.llScissorsCom.scaleY = "1.2".toFloat()

            }
            args === binding.llPaperCom -> {
                binding.llPaperCom.scaleX = "0.85".toFloat()
                binding.llPaperCom.scaleY = "0.85".toFloat()

                // Button ROCK and SCISSORS scale reset
                binding.llRockCom.scaleX = "1.2".toFloat()
                binding.llRockCom.scaleY = "1.2".toFloat()
                binding.llScissorsCom.scaleX = "1.2".toFloat()
                binding.llScissorsCom.scaleY = "1.2".toFloat()

            }
            args === binding.llScissorsCom -> {
                binding.llScissorsCom.scaleX = "0.85".toFloat()
                binding.llScissorsCom.scaleY = "0.85".toFloat()

                // Button ROCK and PAPER scale reset
                binding.llRockCom.scaleX = "1.2".toFloat()
                binding.llRockCom.scaleY = "1.2".toFloat()
                binding.llPaperCom.scaleX = "1.2".toFloat()
                binding.llPaperCom.scaleY = "1.2".toFloat()
            }
            else -> resetChoiceCom()
        }
    }

    private fun resetChoiceCom() {
        binding.llRockCom.scaleX = "1.2".toFloat()
        binding.llRockCom.scaleY = "1.2".toFloat()
        binding.llPaperCom.scaleX = "1.2".toFloat()
        binding.llPaperCom.scaleY = "1.2".toFloat()
        binding.llScissorsCom.scaleX = "1.2".toFloat()
        binding.llScissorsCom.scaleY = "1.2".toFloat()
    }



}




