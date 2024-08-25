package org.example.model.block

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.J_BLOCK_COLOR
import org.example.utils.TetrisTokens.J_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.J_BLOCK_WIDTH
import org.example.utils.UNFILLED_COLOR

/**
 * Represents J block.
 */
class JBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = J_BLOCK_WIDTH, height = J_BLOCK_HEIGHT),
        cells = listOf(
            true, false, false,
            true, true, true,
        ).map { isFilled ->
            val color = if (isFilled) J_BLOCK_COLOR else UNFILLED_COLOR
            Cell(isFilled = isFilled, color = color)
        }
    )
}