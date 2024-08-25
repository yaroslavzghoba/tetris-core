package org.example.model.blocks

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.S_BLOCK_COLOR
import org.example.utils.TetrisTokens.S_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.S_BLOCK_WIDTH
import org.example.utils.UNFILLED_COLOR

/**
 * Represents S block.
 */
class SBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = S_BLOCK_WIDTH, height = S_BLOCK_HEIGHT),
        cells = listOf(
            false, true, true,
            true, true, false,
        ).map { isFilled ->
            val color = if (isFilled) S_BLOCK_COLOR else UNFILLED_COLOR
            Cell(isFilled = isFilled, color = color)
        }
    )
}