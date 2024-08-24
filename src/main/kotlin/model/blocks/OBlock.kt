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
    override var coordinates: Coordinates? = null
        private set

    override val blueprint = Board(
        size = IntSize(width = O_BLOCK_WIDTH, height = O_BLOCK_HEIGHT),
        cells = List(size = O_BLOCK_WIDTH * O_BLOCK_HEIGHT) {
            Cell(isFilled = true, color = O_BLOCK_COLOR)
        }
    )

    override fun moveDown() {
        coordinates?.let {
            coordinates = it.copy(y = it.y + 1)
        } ?: throw IllegalStateException("The block with null coordinates cannot be moved down.")
    }

    override fun moveRight() {
        coordinates?.let {
            coordinates = it.copy(x = it.x + 1)
        } ?: throw IllegalStateException("The block with null coordinates cannot be moved right.")
    }

    override fun moveLeft() {
        coordinates?.let {
            coordinates = it.copy(x = it.x - 1)
        } ?: throw IllegalStateException("The block with null coordinates cannot be moved left.")
    }

    override fun place(initialCoordinates: Coordinates) {
        coordinates = initialCoordinates
    }
}