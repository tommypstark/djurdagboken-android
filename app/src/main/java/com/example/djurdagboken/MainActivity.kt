package com.example.djurdagboken

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.djurdagboken.ui.theme.DjurdagbokenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DjurdagbokenTheme {
                DjurdagbokenScreen()
            }
        }
    }
}

@Composable
fun DjurdagbokenScreen() {

    val animals = listOf(
        Animal("Pancake", "Skäggagam"),
        Animal("Pinpin", "Ögonfransgecko"),
        Animal("Wookie", "Yorkshireterrier")
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Text("Djurdagboken")

            LazyColumn {
                items(animals) { animal ->
                    Text("${animal.name} – ${animal.species}")
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=640")
@Composable
fun DjurdagbokenPreview() {
    DjurdagbokenTheme {
        DjurdagbokenScreen()
    }
}