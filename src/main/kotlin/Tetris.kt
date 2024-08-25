package org.example

import kotlinx.coroutines.delay
import org.example.model.Block
import org.example.model.Board
import org.example.model.Cell
import org.example.utils.*

/**
 * It is the essence of the Tetris game. To start the game, use the [start] method.
 *
 * @param boardSize indicating the size of the game board.
 */
class Tetris(val boardSize: IntSize = TetrisTokens.DEFAULT_BOARD_SIZE) {

    /**
     * The speed of the blocks. The speed 1f is the default speed and is 1 second.
     *
     * Do not set the game speed to 0f to pause the game.
     * Instead, call the appropriate [pause] and [resume] methods.
     */
    var speed: Float = TetrisTokens.DEFAULT_SPEED
        set(value) {
            if (value <= 0f) throw IllegalArgumentException("The game speed cannot be <= 0f")
            field = value
        }

    /**
     * The game board without taking into account the currently moving block.
     */
    private var board = Board(size = boardSize)
        set(value) {
            boardMergedWithBlock =
                if (currentBlock.isPlaced()) value.mergeWithBlock(currentBlock) else value
            field = value
        }

    /**
     * A reflection of the current state of the game board, taking into account the currently moving block.
     */
    private var boardMergedWithBlock = board
        set(value) {
            onBoardChangedListener?.let { it() }
            field = value
        }

    /**
     * The current block, which falls continuously on the game board and is controlled by the player.
     */
    var currentBlock: Block = Block.getAll().random()
        private set

    /**
     * The next block that will be placed on the game board after the current block cannot continue to move down.
     */
    var nextBlock: Block = Block.getAll().random()
        private set

    /**
     * Indicates whether the game was started. To start the game, use the [start] method.
     */
    var isStarted: Boolean = false
        private set

    /**
     * Indicates whether the game is paused. Use the [pause] method to pause the game and [resume] to resume.
     */
    var isPaused: Boolean = false
        private set

    private var onFrameChangedListener: (() -> Unit)? = null
    private var onBoardOverflowListener: (() -> Unit)? = null
    private var onBoardChangedListener: (() -> Unit)? = null
    private var onBlockStoppedListener: (() -> Unit)? = null

    /**
     * Move the current block down 1 cell if possible.
     */
    fun tryMoveBlockDown() {
        if (currentBlock.canBeMovedDownOn(board)) {
            currentBlock.moveDown()
        }
    }

    /**
     * Move the current block 1 cell to the left if possible.
     */
    fun tryMoveBlockLeft() {
        if (currentBlock.canBeMovedLeftOn(board))
            currentBlock.moveLeft()
    }

    /**
     * Move the current block 1 cell to the right if possible.
     */
    fun tryMoveBlockRight() {
        if (currentBlock.canBeMovedRightOn(board))
            currentBlock.moveRight()
    }

    /**
     * Start the Tetris game. Yeah, you definitely don't need anything else.
     * Use the [isStarted] property to check was the game started.
     */
    suspend fun start() {
        while (true) {
            if (isPaused) continue
            onFrameChangedListener?.let { it() }

            // Place the current block if it has not been placed yet.
            if (currentBlock.isNotPlaced()) {
                val x = (boardSize.width / 2) - (currentBlock.blueprint.size.width / 2) - 1
                val initialCoordinates = Coordinates(x = x, y = 0)
                if (currentBlock.canBePlacedAt(initialCoordinates, board)) {
                    currentBlock.apply {
                        place(initialCoordinates)
                        setOnBlockMovedListener {
                            boardMergedWithBlock = board.mergeWithBlock(block = this)
                        }
                    }
                } else {
                    // Invoke the user's event if a new block cannot be placed.
                    onBoardOverflowListener?.let { it() }
                }
            }

            // Set delay between frames.
            val delayInMillis = ((1f / speed) * 1000).toLong()
            delay(delayInMillis)

            // Fix the block in place if it can no longer move.
            val canBlockBeMoved = currentBlock.canBeMovedDownOn(board)
            if (!canBlockBeMoved && currentBlock.isPlaced()) {
                onBlockStoppedListener?.let { it() }
                board = board.mergeWithBlock(currentBlock)
                currentBlock = nextBlock
                nextBlock = Block.getAll().random()
            }

            // Move the current block down 1 cell if possible.
            tryMoveBlockDown()
        }
    }

    /**
     * Pause the game. Use the [isPaused] property to check is the game paused.
     * Use the [resume] method to resume it.
     */
    fun pause() {
        isPaused = true
    }

    /**
     * Resume the game. Use the [isPaused] property to check is the game paused.
     * Use the [pause] method to pause it.
     */
    fun resume() {
        isPaused = false
    }

    /**
     * Get the cell by its coordinates on the game board.
     *
     * The X and Y coordinates start from 0 in the upper left corner of the board.
     * So, on a board with a width and height of 10 cells,
     * the coordinates of the bottom right point are 9 on the X-axis and 9 on the Y-axis.
     *
     * @param coordinates of the cell to the board.
     * @return The cell on the game board is located on the [coordinates].
     */
    fun getCell(coordinates: Coordinates): Cell {
        return boardMergedWithBlock.getCell(coordinates)
    }

    /**
     * Set a listener to be triggered every time a new frame replaces an old one.
     *
     * @param listener that will be triggered every time an event occurs.
     */
    @Deprecated(
        message = "The [setOnFrameChangedListener] method has been deprecated because it is not well suited to " +
                "respond to changes in the state of the game board. It is triggered only when the game frames change " +
                "static, and shifting or rotating the current piece with methods such as [tryMoveBlockDown], " +
                "[tryMoveBlockLeft] goes unnoticed. Use the [setOnBoardChangedListener] method instead. This method " +
                "is also poorly suited for calculating the player's rating and/or changing the speed of the blocks, " +
                "because in the original version, the recalculation took place after the block stopped moving " +
                "completely. Use the [setOnBlockStoppedListener] method instead.",
    )
    fun setOnFrameChangedListener(listener: () -> Unit) {
        onFrameChangedListener = listener
    }

    /**
     * Set up a listener that triggers whenever a new block cannot be spawned
     * because it is blocked by another block, or it goes beyond the game board.
     *
     * @param listener that will be triggered every time an event occurs.
     */
    fun setOnBoardOverflowListener(listener: () -> Unit) {
        onBoardOverflowListener = listener
    }

    /**
     * Set up a listener that triggers whenever the state of the game board changes.
     *
     * @param listener that will be triggered every time an event occurs.
     */
    fun setOnBoardChangedListener(listener: () -> Unit) {
        onBoardChangedListener = listener
    }

    /**
     * Set up a listener that is triggered whenever the current block cannot continue downward
     * due to the boundaries of the game board or due to the obstruction of other blocks.
     *
     * @param listener that will be triggered every time an event occurs.
     */
    fun setOnBlockStoppedListener(listener: () -> Unit) {
        onBlockStoppedListener = listener
    }
}