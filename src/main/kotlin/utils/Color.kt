package org.example.utils

/**
 * Specifies a color of a single cell.
 */
enum class Color {
    UNFILLED, RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE,
}

/**
 * The color should be used for void (unfilled cell).
 */
val UNFILLED_COLOR = Color.UNFILLED

/**
 * Available colors for filled cells. Includes all the colors
 * from defined in [Color] enum except [Color.UNFILLED].
 */
val FILLED_COLORS = Color.entries
    .filter { it !in listOf(Color.UNFILLED) }