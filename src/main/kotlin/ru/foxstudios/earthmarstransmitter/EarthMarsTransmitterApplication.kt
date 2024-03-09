package ru.foxstudios.earthmarstransmitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EarthMarsTransmitterApplication

fun main(args: Array<String>) {
    runApplication<EarthMarsTransmitterApplication>(*args)
}
