package com.example.miniprojet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostFormScreen(
    postId: Int?,
    onSaved: () -> Unit,
    onBack: () -> Unit
) {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Body") }
        )

        Button(
            onClick = {
                if (title.isNotBlank() && body.isNotBlank()) {
                    onSaved()
                }
            }
        ) {
            Text("Enregistrer")
        }

        OutlinedButton(onClick = onBack) {
            Text("Annuler")
        }
    }
}
