package org.example.model.blocks

import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens.Z_BLOCK_COLOR
import org.example.utils.TetrisTokens.Z_BLOCK_HEIGHT
import org.example.utils.TetrisTokens.Z_BLOCK_WIDTH
import org.example.utils.UNFILLED_COLOR

/**
 * Represents Z block.
 */
class ZBlock internal constructor() : Block() {
    override var coordinates: Coordinates? = null
        private set

    override val blueprint = Board(
        size = IntSize(width = Z_BLOCK_WIDTH, height = Z_BLOCK_HEIGHT),
        cells = listOf(
            true, true, false,
            false, true, true,
        ).map { isFilled ->
            val color = if (isFilled) Z_BLOCK_COLOR else UNFILLED_COLOR
            Cell(isFilled = isFilled, color = color)
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