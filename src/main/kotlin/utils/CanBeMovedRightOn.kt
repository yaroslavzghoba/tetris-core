package org.example.utils

import org.example.model.Block
import org.example.model.Board

/**
 * Check if the block can move right 1 cell.
 *
 * @receiver Block to be checked.
 * @param board on which the block moves.
 * @return Boolean value indicating the possibility of moving.
 */
internal fun Block.canBeMovedRightOn(board: Board): Boolean {
    val blockCoordinates = coordinates ?: return false
    repeat(blueprint.size.width) { cellOffsetX ->
        repeat(blueprint.size.height) { cellOffsetY ->
            val cellRelativeCoordinates = Coordinates(x = cellOffsetX, y = cellOffsetY)
            val blockCell = blueprint.getCell(coordinates = cellRelativeCoordinates)
            // Check according cell below on the board
            val cellOnTheRightCoordinates = Coordinates(
                x = blockCoordinates.x + cellOffsetX + 1,
                y = blockCoordinates.y + cellOffsetY,
            )
            if (cellOnTheRightCoordinates.x >= board.size.width)
                return false
            val cellBelowOnBoard = board.getCell(coordinates = cellOnTheRightCoordinates)
            if (blockCell.isFilled && cellBelowOnBoard.isFilled)
                return false
        }
    }
    return true
}