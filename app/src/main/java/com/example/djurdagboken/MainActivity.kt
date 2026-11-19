package com.example.djurdagboken
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.djurdagboken.ui.theme.DjurdagbokenTheme
import com.example.djurdagboken.ui.theme.JungleCard
import com.example.djurdagboken.ui.theme.JungleText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DjurdagbokenTheme {

                val animalViewModel: AnimalViewModel = viewModel()

                DjurdagbokenScreen(
                    uiState = animalViewModel.uiState,
                    onAddAnimal = { animal ->
                        animalViewModel.addAnimal(animal)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DjurdagbokenScreen(
    uiState: AnimalUiState,
    onAddAnimal: (Animal) -> Unit
) {
    val nameState = rememberTextFieldState(initialText = "")
    var selectedSpecies by remember { mutableStateOf<String?>(null) }

    val speciesOptions = listOf(
        "Skäggagam",
        "Ögonfransgecko",
        "Hund",
        "Katt"
    )

    var speciesMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.djurdagboken),
                            contentDescription = "Djurdagboken",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = (-7).dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 6.dp
                    ),
                state = nameState,
                label = { Text("Skriv djurets namn") },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = JungleCard,
                    unfocusedContainerColor = JungleCard,
                    focusedBorderColor = JungleText,
                    unfocusedBorderColor = JungleText,
                    focusedLabelColor = JungleText,
                    unfocusedLabelColor = JungleText,
                    focusedTextColor = JungleText,
                    unfocusedTextColor = JungleText
                )
            )

            ExposedDropdownMenuBox(
                expanded = speciesMenuExpanded,
                onExpandedChange = {
                    speciesMenuExpanded = !speciesMenuExpanded
                }
            ) {
                OutlinedTextField(
                    value = selectedSpecies ?: "",
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Välj djurets art") },

                    shape = RoundedCornerShape(16.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = JungleCard,
                        unfocusedContainerColor = JungleCard,
                        focusedBorderColor = JungleText,
                        unfocusedBorderColor = JungleText,
                        focusedLabelColor = JungleText,
                        unfocusedLabelColor = JungleText,
                        focusedTextColor = JungleText,
                        unfocusedTextColor = JungleText
                    ),

                    modifier = Modifier
                        .menuAnchor(
                            ExposedDropdownMenuAnchorType.PrimaryNotEditable
                        )
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 6.dp
                        ),

                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = speciesMenuExpanded
                        )
                    }
                )
                ExposedDropdownMenu(
                    expanded = speciesMenuExpanded,
                    onDismissRequest = {
                        speciesMenuExpanded = false
                    }
                ) {
                    speciesOptions.forEach { species ->
                        DropdownMenuItem(
                            text = { Text(species) },
                            onClick = {
                                selectedSpecies = species
                                speciesMenuExpanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    onAddAnimal(
                        Animal(
                            name = nameState.text.toString(),
                            species = selectedSpecies!!
                        )
                    )
                },
                enabled = nameState.text.isNotBlank() && selectedSpecies != null,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 0.dp,
                    bottom = 16.dp
                )
            ) {
                Text(text = "Lägg till",
                    fontSize = 20.sp)
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.animals) { animal ->
                    ElevatedCard(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = JungleCard
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = animal.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = animal.species,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DjurdagbokenPreview() {
    DjurdagbokenTheme {
        DjurdagbokenScreen(
            uiState = AnimalUiState(
                animals = sampleAnimals
            ),
            onAddAnimal = { }
        )
    }
}