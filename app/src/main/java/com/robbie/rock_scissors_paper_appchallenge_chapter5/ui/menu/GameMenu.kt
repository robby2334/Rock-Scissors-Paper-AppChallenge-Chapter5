package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.ActivityGameMenuBinding
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form.GetName
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.player.PlayerVsCom
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.player.PlayerVsPlayer

class GameMenu : AppCompatActivity() {
    private lateinit var binding: ActivityGameMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameMenuBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        onClickListener()
        setName()
    }

    private fun onClickListener() {
        binding.ivUserVsComputer.setOnClickListener {
            userVsComputer()


        }
        binding.ivUserVsUser.setOnClickListener {
            userVsUser()

        }
    }

    private fun userVsComputer() {
        val intent = Intent(this, PlayerVsCom::class.java)
        startActivity(intent)
    }
    private fun userVsUser(){
        val intent = Intent(this, PlayerVsPlayer::class.java)
        startActivity(intent)
    }
    private fun setName(){
        binding.tvTitleChoicePlayer.text = String.format(
            getString(
                R.string.text_title,
                GetName(this).userName
            )
        )
        binding.tvUserVsUser.text = String.format(
            getString(
                R.string.text_name_player_vs_player,
                GetName(this).userName
            )
        )
        binding.tvUserVsComputer.text = String.format(
            getString(
                R.string.text_name_player_vs_computer,
                GetName(this).userName
            )
        )
    }

}