package org.example.model.blocks

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.I_BLOCK_COLOR
import org.example.utils.TetrisTokens.I_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.I_BLOCK_WIDTH

/**
 * Represents I block.
 */
class IBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = I_BLOCK_WIDTH, height = I_BLOCK_HEIGHT),
        cells = List(size = I_BLOCK_WIDTH * I_BLOCK_HEIGHT) {
            Cell(isFilled = true, color = I_BLOCK_COLOR)
        }
    )
}