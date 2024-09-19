package domain.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun markdownEditor(
    modifier: Modifier = Modifier,
    markdownText: String,
    onMarkdownChange: (String) -> Unit,
    onImageUpload: () -> Unit
) {
    var isPreviewMode by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = { isPreviewMode = !isPreviewMode }) {
                Text(if (isPreviewMode) "Edit" else "Preview")
            }
        }

        if (!isPreviewMode) {
            BasicTextField(
                value = markdownText,
                onValueChange = { onMarkdownChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Gray)
            )

            Button(onClick = { onImageUpload() }) {
                Text("이미지 업로드")
            }

        } else {
            markdownPreview(markdownText = markdownText)
        }
    }
}

@Composable
fun markdownPreview(markdownText: String) {
    val markdownWithImages = markdownText.split("\n").map { line ->
        if (line.startsWith("![") && line.contains("](") && line.contains(")")) {
            val imageUrl = line.substringAfter("](").substringBefore(")")
            Image(
                painter = painterResource(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        } else {
            Text(line)
        }
    }

    Column {
        markdownWithImages.forEach { it }
    }
}
