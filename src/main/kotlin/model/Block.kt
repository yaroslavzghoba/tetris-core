package org.example.model

import org.example.model.block.JBlock
import org.example.model.block.OBlock
import org.example.model.block.TBlock
import org.example.model.blocks.IBlock
import org.example.model.blocks.LBlock
import org.example.model.blocks.SBlock
import org.example.model.blocks.ZBlock
import org.example.utils.Coordinates
import org.example.utils.isNotPlaced
import org.example.utils.isPlaced

/**
 * Represents a block that is still moving on the game board.
 */
abstract class Block {

    /**
     * Coordinates of block's top left corner in cells relative to the game board.
     * Can be null if it doesn't exist on the board.
     */
    internal var coordinates: Coordinates? = null
        set(value) {
            if (value == coordinates) return
            if (this.isPlaced() && value != null) onBlockMovedListener?.let { it() }
            field = value
        }

    /**
     * The board that represent block's shape.
     * It contains width and height of the block and a list of its cells.
     */
    internal abstract val blueprint: Board

    private var onBlockMovedListener: (() -> Unit)? = null

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
    internal fun moveDown() {
        coordinates?.let {
            coordinates = it.copy(y = it.y + 1)
        }
    }

    /**
     * Move the block to the right by one cell.
     */
    internal fun moveRight() {
        coordinates?.let {
            coordinates = it.copy(x = it.x + 1)
        }
    }

    /**
     * Move the block to the left by one cell.
     */
    internal fun moveLeft() {
        coordinates?.let {
            coordinates = it.copy(x = it.x - 1)
        }
    }

    /**
     * Set a listener that will be triggered whenever the block is moved relative to the board.
     *
     * @param listener that will be triggered every time an event occurs.
     */
    internal fun setOnBlockMovedListener(listener: () -> Unit) {
        onBlockMovedListener = listener
    }

    /**
     * Place a block on the game board.
     *
     * @param initialCoordinates at which the block should be spawned.
     */
    internal fun place(initialCoordinates: Coordinates) {
        coordinates = initialCoordinates
    }

    companion object {
        /**
         * Returns all blocks available in the game.
         */
        internal fun getAll() =
            listOf(IBlock(), JBlock(), LBlock(), OBlock(), SBlock(), TBlock(), ZBlock())
    }
}