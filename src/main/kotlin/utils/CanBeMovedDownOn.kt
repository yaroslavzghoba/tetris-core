package org.example.utils

import org.example.model.Block
import org.example.model.Board

/**
 * Check if the block can move down 1 cell.
 *
 * @receiver Block to be checked.
 * @param board on which the block moves.
 * @return Boolean value indicating the possibility of moving.
 */
internal fun Block.canBeMovedDownOn(board: Board): Boolean {
    val blockCoordinates = coordinates ?: return false
    repeat(blueprint.size.width) { cellOffsetX ->
        repeat(blueprint.size.height) { cellOffsetY ->
            val cellRelativeCoordinates = Coordinates(x = cellOffsetX, y = cellOffsetY)
            val blockCell = blueprint.getCell(coordinates = cellRelativeCoordinates)
            // Check according cell below on the board
            val cellBelowOnBoardCoordinates = Coordinates(
                x = blockCoordinates.x + cellOffsetX,
                y = blockCoordinates.y + cellOffsetY + 1,
            )
            if (cellBelowOnBoardCoordinates.y >= board.size.height)
                return false
            val cellBelowOnBoard = board.getCell(coordinates = cellBelowOnBoardCoordinates)
            if (blockCell.isFilled && cellBelowOnBoard.isFilled)
                return false
        }
    }
    return true
}