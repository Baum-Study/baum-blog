package domain.api

import domain.model.BlogImage
import java.time.LocalDate

suspend fun fetchImagesFromApi(): List<BlogImage> {
    return listOf(
        BlogImage("미니언 개구리", "img/minion_frog.png", "minion frog", LocalDate.now()),
        BlogImage("뚱이 개구리", "img/pink_frog.png", "pink frog", LocalDate.now()),
        BlogImage("피카추 개구리", "img/yellow_frog.png", "yellow frog", LocalDate.now())
    )
}