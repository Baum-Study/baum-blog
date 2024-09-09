package example.baumblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BaumBlogApplication

fun main(args: Array<String>) {
    runApplication<BaumBlogApplication>(*args)
}
