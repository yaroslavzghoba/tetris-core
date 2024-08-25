# Tetris Core 

The `tetris-core` library is an attempt to replicate the gameplay of the Tetris game released in the 80s using the Kotlin programming language and make it publicly available as an easy-to-use API.

## Installing

1. Add the JitPack repository to your `settings.gradle.kts` file.

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Other repositories
        maven(url = "https://jitpack.io")  // This one
    }
}
```

2. Add a dependency to your `build.gradle.kts` file at the module level.

```kotlin
dependencies {
    implementation("com.github.yaroslavzghoba:tetris-core:1.0.1")
}
```

## Usage

1. Create an object of class `Tetris` and call its method `start` in a separate thread to start the game. Optionally, you can also pass the size of the game board in the class constructor.

```kotlin
fun main() = coroutineScope {
    val boardSize = IntSize(width = 10, height = 20)
    val game = Tetris(boardSize = boardSize)
    launch {
        game.start()
    }
}
```

2. After that, you will be able to access any cell on the board by its coordinates to display the current state of the entire game board.

```kotlin
val rows = mutableListOf<Cell>()
repeat(game.boardSize.height) { y ->
    val row = List(game.boardSize.width) { x ->
        // Get the cell by its coordinates on the board
        val coordinates = Coordinates(x, y)
        game.getCell(coordinates)
    }
    rows.add(row)
}
```

3. Using this code, you can display the current state of the game board, including the currently moving block, at a single time. To react dynamically to board state changes, wrap the code above around the `setOnBoardChangedListener` method. Now, this code will be triggered every time the board state changes.

```kotlin
game.setOnBoardChangedListener {
    // Imagine that this is a code for displaying a game board.
    // It'll be executed every time the board state changes.
}
```

4. By calling the `tryMoveBlockDown`, `tryMoveBlockLeft` and `tryMoveBlockRight` methods of the `Tetris` class, you can move the block down, left and right, respectively.

In fact, the list of available properties and methods in the `Tetris` class is somewhat larger. See also [the class API documentation.](https://misty-polonium-bfd.notion.site/The-Tetris-class-API-docs-86fd17bcbd3c4654b6b39de76d0ec742)

## Roadmap

The tetris-core library is not yet complete and will continue to evolve. Check out [the library's roadmap](https://misty-polonium-bfd.notion.site/The-tetris-core-library-0030b4781d9548ec88775d5edd36c85f) to see what is planned to be added or changed, as well as what is currently being worked on.