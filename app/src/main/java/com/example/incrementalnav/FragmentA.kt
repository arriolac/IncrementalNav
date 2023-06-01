package com.example.incrementalnav

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController

class FragmentA : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return LinearLayout(requireContext()).apply {
      orientation = LinearLayout.VERTICAL
      addView(
        TextView(context).apply {
          layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
          )
          gravity = Gravity.CENTER
          text = "Fragment A"
          setTextSize(TypedValue.COMPLEX_UNIT_SP, 50F)
        }
      )
      addView(
        Button(context).apply {
          layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
          )
          text = "Go to Fragment B"
          setOnClickListener {
            navigate(findNavController())
          }
        }
      )
    }
  }

  private fun navigate(navController: NavController) {
    navController.navigate(R.id.fragmentB)
  }
}
