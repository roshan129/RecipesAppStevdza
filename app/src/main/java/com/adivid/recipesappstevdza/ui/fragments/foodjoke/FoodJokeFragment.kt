package com.adivid.recipesappstevdza.ui.fragments.foodjoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adivid.recipesappstevdza.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_joke, container, false)
    }

}