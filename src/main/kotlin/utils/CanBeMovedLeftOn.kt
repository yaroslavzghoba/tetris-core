package org.example.utils

import org.example.model.Block
import org.example.model.Board

/**
 * Check if the block can move left 1 cell.
 *
 * @receiver Block to be checked.
 * @param board on which the block moves.
 * @return Boolean value indicating the possibility of moving.
 */
internal fun Block.canBeMovedLeftOn(board: Board): Boolean {
    val blockCoordinates = coordinates ?: return false
    repeat(blueprint.size.width) { cellOffsetX ->
        repeat(blueprint.size.height) { cellOffsetY ->
            val cellRelativeCoordinates = Coordinates(x = cellOffsetX, y = cellOffsetY)
            val blockCell = blueprint.getCell(coordinates = cellRelativeCoordinates)
            // Check according cell below on the board
            val cellOnTheLeftCoordinates = Coordinates(
                x = blockCoordinates.x + cellOffsetX - 1,
                y = blockCoordinates.y + cellOffsetY,
            )
            if (cellOnTheLeftCoordinates.x < 0)
                return false
            val cellBelowOnBoard = board.getCell(coordinates = cellOnTheLeftCoordinates)
            if (blockCell.isFilled && cellBelowOnBoard.isFilled)
                return false
        }
    }
    return true
}