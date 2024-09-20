package domain.model

import java.time.LocalDate

data class BlogImage(
    val name: String,
    val url: String,
    val description: String,
    val date: LocalDate,
)
