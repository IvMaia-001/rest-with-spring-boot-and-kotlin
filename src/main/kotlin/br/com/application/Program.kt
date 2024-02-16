package br.com.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Program

fun main(args: Array<String>) {
	runApplication<Program>(*args)
}
