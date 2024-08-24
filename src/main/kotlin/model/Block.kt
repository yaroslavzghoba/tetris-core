package org.example.model

import org.example.model.block.JBlock
import org.example.model.block.OBlock
import org.example.model.block.TBlock
import org.example.model.blocks.IBlock
import org.example.model.blocks.LBlock
import org.example.model.blocks.SBlock
import org.example.model.blocks.ZBlock
import org.example.utils.Coordinates

/**
 * Represents a block that is still moving on the game board.
 */
abstract class Block {

    /**
     * Coordinates of block's top left corner in cells relative to the game board.
     * Can be null if it doesn't exist on the board.
     */
    abstract val coordinates: Coordinates?

    /**
     * The board that represent block's shape.
     * It contains width and height of the block and a list of its cells.
     */
    abstract val blueprint: Board

    /**
     * Rotate the shape to the right by 90 degrees.
     */
    // TODO: Define the function
    // abstract fun rotateRight()

    /**
     * Rotate the shape to the left by 90 degrees.
     */
    // TODO: Define the function
    // abstract fun rotateLeft()

    /**
     * Move the block down by one cell.
     */
    internal abstract fun moveDown()

    /**
     * Move the block to the right by one cell.
     */
    internal abstract fun moveRight()

    /**
     * Move the block to the left by one cell.
     */
    internal abstract fun moveLeft()

    /**
     * Place a block on the game board.
     *
     * @param initialCoordinates at which the block should be spawned.
     */
    internal abstract fun place(initialCoordinates: Coordinates)

    companion object {
        /**
         * Returns all blocks available in the game.
         */
        internal fun getAll() =
            listOf(IBlock(), JBlock(), LBlock(), OBlock(), SBlock(), TBlock(), ZBlock())
    }
}