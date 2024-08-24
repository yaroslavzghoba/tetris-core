package utils

import org.example.model.Board
import org.example.model.Cell
import org.example.model.block.JBlock
import org.example.utils.TetrisTokens
import org.example.utils.canBeMovedDownOn
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class CanBeMovedDownOnTest {

    @Test
    fun `Return false in response to an unplaced block`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val canBeMovedDown = JBlock().canBeMovedDownOn(board)
        assertFalse(canBeMovedDown)
    }

    @Test
    fun `Return false if there is a block below`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height) { index ->
            index in (boardSize.width * 2)..(boardSize.width * 3)
        }.map { Cell(isFilled = it) }
        val board = Board(size = boardSize, cells = cells)

    }
}