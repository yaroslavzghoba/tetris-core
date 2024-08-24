package org.example.utils

import org.example.model.Block
import org.example.model.Board

/**
 * Check if the block can be placed.
 *
 * @receiver Block to be checked.
 * @param coordinates at which the block should be placed.
 * @param board on which the block should be placed.
 * @return Boolean value indicating the possibility of placing.
 */
internal fun Block.canBePlacedAt(coordinates: Coordinates, board: Board): Boolean {
    repeat(this.blueprint.size.height) { cellOffsetY ->
        repeat(this.blueprint.size.width) { cellOffsetX ->
            val cellCoordinates = Coordinates(
                x = coordinates.x + cellOffsetX,
                y = coordinates.y + cellOffsetY,
            )
            if (cellCoordinates.x !in 0..<board.size.width) return false
            if (cellCoordinates.y !in 0..<board.size.height) return false
            val correspondingCellOnBoard = board.getCell(coordinates = cellCoordinates)
            if (correspondingCellOnBoard.isFilled) return false
        }
    }
    return true
}