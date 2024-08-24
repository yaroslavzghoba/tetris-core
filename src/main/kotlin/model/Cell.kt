package org.example.model

import org.example.utils.Color
import org.example.utils.FILLED_COLORS
import org.example.utils.UNFILLED_COLOR

/**
 * Represents a single cell at the game board.
 *
 * @param isFilled specifies whether this cell on the board is a real cell or not.
 * A filled cell has its own collision and not filled not.
 * @param color of the cell on the board. By default, it's [UNFILLED_COLOR] if the cell is unfilled
 * and any of the [FILLED_COLORS].
 */
data class Cell internal constructor(
    val isFilled: Boolean,
    val color: Color = if (isFilled) FILLED_COLORS.random() else UNFILLED_COLOR,
)