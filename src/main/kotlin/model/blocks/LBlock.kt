package org.example.model.blocks

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.L_BLOCK_COLOR
import org.example.utils.TetrisTokens.L_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.L_BLOCK_WIDTH
import org.example.utils.UNFILLED_COLOR

/**
 * Represents L block.
 */
class LBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = L_BLOCK_WIDTH, height = L_BLOCK_HEIGHT),
        cells = listOf(
            false, false, true,
            true, true, true,
        ).map { isFilled ->
            val color = if (isFilled) L_BLOCK_COLOR else UNFILLED_COLOR
            Cell(isFilled = isFilled, color = color)
        }
    )
}