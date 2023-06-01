package com.example.incrementalnav.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
