package com.example.miniprojet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miniprojet.viewModel.PostViewModel

@Composable
fun PostDetailScreen(
    postId: Int,
    vm: PostViewModel = viewModel(),
    onEdit: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Détails du post ID = $postId",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = onEdit,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Modifier")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Retour")
        }
    }
}