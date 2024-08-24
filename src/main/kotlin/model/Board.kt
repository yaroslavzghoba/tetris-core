package org.example.model

import org.example.utils.Coordinates
import org.example.utils.IntSize

/**
 * Represent game board filled by cells.
 *
 * @param size of the board in cells. Both values must be greater than 0.
 * @param cells placed at the board. The size of the list must be equals to
 * the width of the board multiplied by its height.
 */
class Board internal constructor(
    val size: IntSize,
    private val cells: List<Cell> =
        List(size = size.width * size.height) { Cell(isFilled = false) },
) {
    private var _cells: List<Cell> = cells
        set(value) {
            validateCellsList(cells = value)
            field = value
        }

    init {
        validateArguments(size = size, cells = cells)
    }

    /**
     * Validate arguments passed in the constructor.
     *
     * @param size of the board in cells. Both values must be greater than 0.
     * @param cells placed at the board. The size of the list must be equals to
     * the width of the board multiplied by its height.
     *
     * @throws IllegalArgumentException if at least one of the conditions described above are not met.
     */
    private fun validateArguments(size: IntSize, cells: List<Cell>) {
        require(value = size.width > 0)
        { "The width of the game board must be greater than 0. Your board has width ${size.width} cells." }
        require(value = size.height > 0)
        { "The height of the game board must be greater than 0. Your board has height ${size.height} cells." }
        validateCellsList(cells = cells)
    }

    /**
     * Validate the list of cells placed on the board. The size of the list must be equals to
     * the width of the board multiplied by its height.
     *
     * @throws IllegalArgumentException if the condition described above is not met.
     */
    private fun validateCellsList(cells: List<Cell>) {
        require(value = cells.size == size.width * size.height) {
            "The number of cells on the board must be equal to the width of the board multiplied by the height. " +
                    "The size of the passed list of cells equals to ${cells.size}."
        }
    }

    /**
     * Get the cell by its coordinates.
     *
     * @param coordinates of the cell relative to the board.
     * @return A cell with the corresponding coordinates.
     */
    fun getCell(coordinates: Coordinates): Cell {
        val index = coordinates.y * size.width + coordinates.x
        return _cells[index]
    }

    /**
     * Create a copy of the board with the block cells transferred to it.
     *
     * @param block whose cells should be transferred to the board cells.
     * @return The board with block cells overlaid on it.
     * @throws IllegalArgumentException if the block does not have coordinates or if it extends beyond the board.
     */
    internal fun mergeWithBlock(block: Block): Board {
        val mergedCells = mergeCellsWithBlock(block = block)
        return Board(size = size, cells = mergedCells)
    }

    /**
     * Returns board cells with block cells overlaid on them.
     *
     * @param block whose cells should be transferred to the board cells.
     * @return Board cells with block cells overlaid on them.
     * @throws IllegalArgumentException if the block does not have coordinates or if it extends beyond the board.
     */
    private fun mergeCellsWithBlock(block: Block): List<Cell> {
        validateBlockPlacement(block)
        val blockCoordinates = block.coordinates!!
        val mergedCells = _cells.toMutableList()
        repeat(block.blueprint.size.width) { cellOffsetX ->
            repeat(block.blueprint.size.height) { cellOffsetY ->
                val cellRelativeCoordinates = Coordinates(x = cellOffsetX, y = cellOffsetY)
                val cellCoordinates = Coordinates(
                    x = blockCoordinates.x + cellRelativeCoordinates.x,
                    y = blockCoordinates.y + cellRelativeCoordinates.y,
                )
                val cell = block.blueprint.getCell(coordinates = cellRelativeCoordinates)
                val indexOnBoard = cellCoordinates.y * size.width + cellCoordinates.x
                mergedCells[indexOnBoard] = cell
            }
        }
        return mergedCells
    }

    /**
     * Check the placement of the block, including its coordinates and compliance with the board boundaries.
     *
     * @param block The unit whose placement relative to the board should be checked.
     * @throws IllegalArgumentException if the block does not have coordinates or if it extends beyond the board.
     */
    private fun validateBlockPlacement(block: Block) {
        val blockCoordinates = block.coordinates
            ?: throw IllegalArgumentException("The board cannot be merged with the block that doesn't have coordinates")
        require(blockCoordinates.x >= 0) { "The block cannot extend beyond the left side of the board" }
        require(blockCoordinates.y >= 0) { "The block cannot extend beyond the top side of the board" }
        require(blockCoordinates.x + block.blueprint.size.width <= this.size.width)
        { "The block cannot extend beyond the right side of the board" }
        require(blockCoordinates.y + block.blueprint.size.height <= this.size.height)
        { "The block cannot extend beyond the bottom side of the board" }
    }
}