package org.example.model.block

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.T_BLOCK_COLOR
import org.example.utils.TetrisTokens.T_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.T_BLOCK_WIDTH
import org.example.utils.UNFILLED_COLOR

/**
 * Represents T block.
 */
class TBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = T_BLOCK_WIDTH, height = T_BLOCK_HEIGHT),
        cells = listOf(
            false, true, false,
            true, true, true,
        ).map { isFilled ->
            val color = if (isFilled) T_BLOCK_COLOR else UNFILLED_COLOR
            Cell(isFilled = isFilled, color = color)
        }
    )
}