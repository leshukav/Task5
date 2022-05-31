import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test


class mainKtTest {
    @Test
    fun print() {
        val service = WallService()
        val postNew = service.add(Post(id = 1, text = "Test"))
        service.printPosts()
    }

    @Test
    fun addTest() {
        val service = WallService()
        val postNew = service.add(Post(id = 1, text = "Test"))
        assertEquals(1, postNew.id)
    }

    @Test
    fun addTestF() {
        val service = WallService()
        val postNew = service.add(Post(id = 1, text = "Test"))
        assertEquals(4, postNew.id)
    }

    @Test
    fun updateTestTrue() {
        val service = WallService()
        service.add(Post(id = 1, text = "Hello"))
        service.add(Post(id = 2, text = "world"))

        val update = Post(id = 1, text = "Upps")
        val result = service.update(update)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val service = WallService()
        service.add(Post(id = 1, text = "Hello"))
        service.add(Post(id = 2, text = "world"))
        val update = Post(id = 3, text = "Upps")
        val result = service.update(update)
        assertTrue(result)

    }
}