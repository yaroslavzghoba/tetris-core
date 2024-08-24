package org.example.utils

import org.example.model.Block

/**
 * Indicates whether the block is placed, i.e. whether it has its own coordinates.
 *
 * @receiver The block whose placement must be checked
 * @return Boolean value indicating the presence of a block placement.
 */
internal fun Block.isPlaced() = this.coordinates != null

/**
 * Indicates whether the block is not placed, i.e. whether it doesn't have its own coordinates.
 *
 * @receiver The block whose placement must be checked
 * @return Boolean value indicating the presence of a block placement.
 */
internal fun Block.isNotPlaced() = this.coordinates == null