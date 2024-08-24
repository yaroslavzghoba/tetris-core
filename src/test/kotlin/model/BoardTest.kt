package model

import org.example.model.Board
import org.example.model.Cell
import org.example.model.block.JBlock
import org.example.model.block.TBlock
import org.example.utils.Coordinates
import org.example.utils.IntSize
import org.example.utils.TetrisTokens
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue


class BoardTest {

    @Test
    fun `Don't init with zero width`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE.copy(width = 0)
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            Board(size = boardSize)
        }
    }

    @Test
    fun `Don't init with zero height`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE.copy(height = 0)
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            Board(size = boardSize)
        }
    }

    @Test
    fun `Don't init with an incorrect number of cells`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height + 1) { Cell(isFilled = false) }
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            Board(TetrisTokens.DEFAULT_BOARD_SIZE, cells = cells)
        }
    }

    @Test
    fun `Return the cell by the block's top left coordinates`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height) { index ->
            Cell(isFilled = index == 0)
        }
        val board = Board(size = boardSize, cells = cells)
        val cell = board.getCell(coordinates = Coordinates(x = 0, y = 0))
        assertTrue(cell.isFilled)
    }

    @Test
    fun `Return the cell by the block's top right coordinates`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height) { index ->
            Cell(isFilled = index == boardSize.width - 1)
        }
        val board = Board(size = boardSize, cells = cells)
        val cellCoordinates = Coordinates(x = boardSize.width - 1, y = 0)
        val cell = board.getCell(coordinates = cellCoordinates)
        assertTrue(cell.isFilled)
    }

    @Test
    fun `Return the cell by the block's bottom left coordinates`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height) { index ->
            Cell(isFilled = index == boardSize.width * (boardSize.height - 1))
        }
        val board = Board(size = boardSize, cells = cells)
        val cellCoordinates = Coordinates(x = 0, y = boardSize.height - 1)
        val cell = board.getCell(coordinates = cellCoordinates)
        assertTrue(cell.isFilled)
    }

    @Test
    fun `Return the cell by the block's bottom right coordinates`() {
        val boardSize = TetrisTokens.DEFAULT_BOARD_SIZE
        val cells = List(size = boardSize.width * boardSize.height) { index ->
            Cell(isFilled = index == (boardSize.width * boardSize.height) - 1)
        }
        val board = Board(size = boardSize, cells = cells)
        val cellCoordinates = Coordinates(x = boardSize.width - 1, y = boardSize.height - 1)
        val cell = board.getCell(coordinates = cellCoordinates)
        assertTrue(cell.isFilled)
    }

    @Test
    fun `Fail if the block wasn't placed`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val block = JBlock()
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            board.mergeWithBlock(block)
        }
    }

    @Test
    fun `Fail if the block goes beyond the left border`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val blockCoordinates = Coordinates(x = -1, y = 0)
        val block = JBlock().apply { place(initialCoordinates = blockCoordinates) }
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            board.mergeWithBlock(block)
        }
    }

    @Test
    fun `Fail if the block goes beyond the top border`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val blockCoordinates = Coordinates(x = 0, y = -1)
        val block = JBlock().apply { place(initialCoordinates = blockCoordinates) }
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            board.mergeWithBlock(block)
        }
    }

    @Test
    fun `Fail if the block goes beyond the right border`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val blockCoordinates = Coordinates(x = board.size.width, y = 0)
        val block = JBlock().apply { place(initialCoordinates = blockCoordinates) }
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            board.mergeWithBlock(block)
        }
    }

    @Test
    fun `Fail if the block goes beyond the bottom border`() {
        val board = Board(size = TetrisTokens.DEFAULT_BOARD_SIZE)
        val blockCoordinates = Coordinates(x = 0, y = board.size.height)
        val block = JBlock().apply { place(initialCoordinates = blockCoordinates) }
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            board.mergeWithBlock(block)
        }
    }

    @Test
    fun `Return the board merged with block's cells`() {
        val boardSize = IntSize(width = 10, height = 10)
        val expectedCells = listOf(
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, true, false,
            false, false, false, false, false, false, false, true, true, true,
        ).map { Cell(isFilled = it) }
        val expectedBoard = Board(size = boardSize, cells = expectedCells)
        val blockCoordinates = Coordinates(x = 7, y = 8)
        val block = TBlock().apply { place(blockCoordinates) }
        val actualBoard = Board(size = boardSize).mergeWithBlock(block)

        var areCellsSame = true
        repeat(boardSize.width) { cellOffsetX ->
            repeat(boardSize.height) { cellOffsetY ->
                val cellCoordinates = Coordinates(x = cellOffsetX, y = cellOffsetY)
                val expectedCell = expectedBoard.getCell(coordinates = cellCoordinates)
                val actualCell = actualBoard.getCell(coordinates = cellCoordinates)
                if (expectedCell.isFilled != actualCell.isFilled)
                    areCellsSame = false
            }
        }
        assertTrue(areCellsSame)
    }
}