package org.example.model.block

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.O_BLOCK_COLOR
import org.example.utils.TetrisTokens.O_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.O_BLOCK_WIDTH

/**
 * Represents O block.
 */
class OBlock internal constructor() : Block() {

    override val blueprint = Board(
        size = IntSize(width = O_BLOCK_WIDTH, height = O_BLOCK_HEIGHT),
        cells = List(size = O_BLOCK_WIDTH * O_BLOCK_HEIGHT) {
            Cell(isFilled = true, color = O_BLOCK_COLOR)
        }
    )
}