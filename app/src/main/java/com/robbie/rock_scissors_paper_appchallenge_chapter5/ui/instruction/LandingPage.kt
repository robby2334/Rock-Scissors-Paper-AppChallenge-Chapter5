package com.robbie.rock_scissors_paper_appchallenge_chapter5.ui.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.robbie.rock_scissors_paper_appchallenge_chapter5.databinding.FragmentLandingPageBinding

class LandingPage : Fragment() {

    private lateinit var binding : FragmentLandingPageBinding

    private var title: String? = null
    private var decs: String? = null
    private var img: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_INTRO_TITLE)
            decs = it.getString(ARG_PARAM_INTRO_DESC)
            img = it.getString(ARG_PARAM_INTRO_IMG)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLandingPageBinding.inflate(inflater, container , false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = title.orEmpty()
        binding.tvDesc.text =decs.orEmpty()
        Glide.with(this)
            .load(img)
            .into(binding.ivIntro)
    }

    companion object {
        private const val ARG_PARAM_INTRO_TITLE = "ARG_PARAM_INTRO_TITLE"
        private const val ARG_PARAM_INTRO_DESC = "ARG_PARAM_INTRO_DESC"
        private const val ARG_PARAM_INTRO_IMG = "ARG_PARAM_INTRO_IMG_RES"
        @JvmStatic
        fun newInstance(title: String, decs: String, img : String) =
            LandingPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_INTRO_TITLE, title)
                    putString(ARG_PARAM_INTRO_DESC, decs)
                    putString(ARG_PARAM_INTRO_IMG, img)

                }
            }
    }

}