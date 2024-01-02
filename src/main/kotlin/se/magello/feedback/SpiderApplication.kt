package se.magello.feedback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpiderApplication

fun main(args: Array<String>) {
	runApplication<SpiderApplication>(*args)
}
