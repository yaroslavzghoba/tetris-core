package org.example.utils

internal object TetrisTokens {
    val DEFAULT_BOARD_SIZE =
        IntSize(width = 10, height = 20)
    const val DEFAULT_SPEED = 1f

    const val I_BLOCK_WIDTH = 4
    const val I_BLOCK_HEIGHT = 1
    val I_BLOCK_COLOR = Color.CYAN

    const val J_BLOCK_WIDTH = 3
    const val J_BLOCK_HEIGHT = 2
    val J_BLOCK_COLOR = Color.BLUE

    const val L_BLOCK_WIDTH = 3
    const val L_BLOCK_HEIGHT = 2
    val L_BLOCK_COLOR = Color.ORANGE

    const val O_BLOCK_WIDTH = 2
    const val O_BLOCK_HEIGHT = 2
    val O_BLOCK_COLOR = Color.YELLOW

    const val S_BLOCK_WIDTH = 3
    const val S_BLOCK_HEIGHT = 2
    val S_BLOCK_COLOR = Color.GREEN

    const val T_BLOCK_WIDTH = 3
    const val T_BLOCK_HEIGHT = 2
    val T_BLOCK_COLOR = Color.PURPLE

    const val Z_BLOCK_WIDTH = 3
    const val Z_BLOCK_HEIGHT = 2
    val Z_BLOCK_COLOR = Color.RED
}