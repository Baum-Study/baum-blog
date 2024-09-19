package domain.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import domain.api.fetchImagesFromApi
import domain.model.BlogImage

@Composable
fun homeScreen() {
    var blogImages by remember { mutableStateOf<List<BlogImage>>(emptyList()) }

    LaunchedEffect(Unit) { blogImages = fetchImagesFromApi() }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (blogImages.isEmpty()) {
                Text("이미지 가져오는 중...", style = MaterialTheme.typography.body1)
            } else {
                blogImages.chunked(2).forEach { rowImages ->
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowImages.forEach { imageItem ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(imageItem.url),
                                    contentDescription = imageItem.description,
                                    modifier = Modifier
                                        .size(250.dp)
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(imageItem.name, style = MaterialTheme.typography.h6)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}