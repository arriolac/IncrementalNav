package com.example.incrementalnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.reflect.getDeclaredComposableMethod
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

class ComposeFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val composableName = arguments?.getString(COMPOSABLE_NAME)
      ?: throw IllegalStateException("No value for $COMPOSABLE_NAME provided")

    return try {
      // FIXME: Reflection only works for composables inside ComposeFragment. Need to reflect
      // to top-leve functions instead
      val method = javaClass.getDeclaredComposableMethod(composableName)
      val composeView = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
          method.invoke(currentComposer, this@ComposeFragment)
        }
      }
      composeView
    } catch (e : NoSuchMethodException) {
      e.printStackTrace()
      null
    }
  }

  companion object {
    const val COMPOSABLE_NAME = "composableName"
  }

  @Composable
  fun ComposeScreenA() {
    Surface {
      Column {
        Text(text = "Compose Screen A")
        Button(onClick = {
          // Navigate
        }) {
          Text(text = "Go to Compose Screen B")
        }
      }
    }
  }
}

fun Fragment.content(content: @Composable () -> Unit): ComposeView {
  return ComposeView(requireContext()).apply {
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    setContent(content)
  }
}
