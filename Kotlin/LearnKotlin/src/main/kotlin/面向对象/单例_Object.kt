object MusicPlayer {
    val state: Int = 0

    fun play(url: String) {}

    fun stop() {}
}

fun main(args: Array<String>) {
    MusicPlayer.state
}