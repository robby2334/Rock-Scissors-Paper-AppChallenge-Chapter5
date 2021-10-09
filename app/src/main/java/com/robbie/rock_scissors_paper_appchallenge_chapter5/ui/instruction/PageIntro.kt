package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.instruction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.ActivityPageIntroBinding
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form.FormFragmentListener
import com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.form.FormLogin
import com.robbie.rock_scissors_paper_appchallenge_chapter5.utils.ViewPagerHelp

class PageIntro : AppCompatActivity(), FormFragmentListener {

    private lateinit var binding: ActivityPageIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bind()
        initViewPager()

    }

    private fun bind() {
        binding = ActivityPageIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViewPager() {
        val fragmentLandingPageBinding = ViewPagerHelp(supportFragmentManager, lifecycle)
        fragmentLandingPageBinding.addFragment(
            LandingPage.newInstance(
                "Welcome",
                "this is a game suit",
                "https://www.animatedimages.org/data/media/523/animated-hello-image-0044.gif"
            )
        )
        fragmentLandingPageBinding.addFragment(
            LandingPage.newInstance(
                "Suit Game",
                "You can choose your enemy, against other players or Com.",
                "https://play-lh.googleusercontent.com/RhcxK7ITka-n5NnEu5aSHbb0cYdIzQ-8P40afm4yx_1ow8Z5QQ5e7bS0VxmAx-Mm7RQ"
            )
        )
        fragmentLandingPageBinding.addFragment(
            FormLogin()
        )

        binding.vpIntro.apply {
            adapter = fragmentLandingPageBinding
        }
        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when {
                    position == 0 -> {
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvPrevious.visibility = View.INVISIBLE
                        binding.tvPrevious.isEnabled = false
                    }
                    position < fragmentLandingPageBinding.itemCount - 1 -> {
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvPrevious.visibility = View.VISIBLE
                        binding.tvPrevious.isEnabled = true
                    }
                    position == fragmentLandingPageBinding.itemCount - 1 -> {
                        binding.tvNext.visibility = View.INVISIBLE
                        binding.tvNext.isEnabled = false
                        binding.tvPrevious.visibility = View.VISIBLE
                        binding.tvPrevious.isEnabled = true
                    }
                }
                super.onPageSelected(position)
            }
        })
        binding.dotsIndicator.setViewPager2(binding.vpIntro)
        binding.tvNext.setOnClickListener {
            if (getNextIndex() != -1) {
                binding.vpIntro.setCurrentItem(getNextIndex(), true)
            }
        }
        binding.tvPrevious.setOnClickListener {
            if (getPreviousIndex() != -1) {
                binding.vpIntro.setCurrentItem(getPreviousIndex(), true)
            }
        }
    }

    private fun getPreviousIndex(): Int {
        val currentPageIdx = binding.vpIntro.currentItem
        return if (currentPageIdx - 1 >= 0) {
            currentPageIdx - 1
        } else {
            -1
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.vpIntro.adapter?.itemCount
        val currentPageIdx = binding.vpIntro.currentItem
        var selectedIdx = -1
        maxPages?.let {
            if (currentPageIdx + 1 < maxPages) {
                selectedIdx = currentPageIdx + 1
            }
        }
        return selectedIdx
    }


    override fun onNameSubmitted(text: String) {
        if (text.isEmpty()) {
            Snackbar.make(binding.root, R.string.text_error_input_name, Snackbar.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Hallo $text", Toast.LENGTH_SHORT).show()

        }

    }
}



