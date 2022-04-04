package com.example.testapp.model

object Model {
    data class Poke(
        val name: String,
        val sprites: Sprites,
        val stats: ArrayList<Stats>
    )

    data class Sprites(
        val back_default: String?,
        val front_default: String?
    )

    data class Stats(
        val base_stat: Int,
        val stat: Stat
    )

    data class Stat(
        val name: String
    )
}