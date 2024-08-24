import org.example.Tetris
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class TetrisTest {

    @Test
    fun `Fail when trying to set a non-positive speed`() {
        assertFailsWith(exceptionClass = IllegalArgumentException::class) {
            Tetris().speed = 0f
        }
    }
}