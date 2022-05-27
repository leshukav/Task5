import junit.framework.Assert.assertTrue
import org.junit.Test

class mainKtTest {
   @Test
      fun update(){
         val service = WallService()
         service.add(Post(id = 1, text = "Hello"))
         service.add(Post(id=2, text = "world"))

      val update = Post(id = 1, text = "Upps")
      val result = service.update(update)
      assertTrue(result)
      }

   @Test
    fun updateFalse() {
       val service = WallService()
      service.add(Post(id = 1, text = "Hello"))
      service.add(Post(id=2, text = "world"))
      val update = Post(id = 3, text = "Upps")
      val result = service.update(update)
      assertTrue(result)

    }
}